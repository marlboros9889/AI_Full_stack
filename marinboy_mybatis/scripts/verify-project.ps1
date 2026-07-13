param(
    [switch]$SkipTests
)

$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $PSScriptRoot
$failures = [System.Collections.Generic.List[string]]::new()

Write-Host '[1/5] MyBatis XML validation'
Get-ChildItem (Join-Path $root 'src\main\resources\mybatis\mapper') -Filter '*.xml' | ForEach-Object {
    try {
        [xml](Get-Content -LiteralPath $_.FullName -Raw) | Out-Null
    } catch {
        $failures.Add("Invalid XML: $($_.Name) - $($_.Exception.Message)")
    }
}

Write-Host '[2/5] Reservation SQL validation'
$reservationMapper = Get-Content -LiteralPath (Join-Path $root 'src\main\resources\mybatis\mapper\salon-reservation-mapper.xml') -Raw
if ($reservationMapper -notmatch 'countOverlappingReservation') {
    $failures.Add('Missing overlapping reservation query.')
}
if ($reservationMapper -match '(?m)^\s*[<>]\s*#\{reservationDateTime\}') {
    $failures.Add('Unescaped XML comparison operator in reservation query.')
}

Write-Host '[3/5] DAO and admin API validation'
$reservationDao = Get-Content -LiteralPath (Join-Path $root 'src\main\java\com\marinboy\dao\SalonReservationDao.java') -Raw
if ($reservationDao -match 'countActiveReservationAt') {
    $failures.Add('Obsolete countActiveReservationAt DAO method remains.')
}
$reservationController = Get-Content -LiteralPath (Join-Path $root 'src\main\java\com\marinboy\controller\SalonReservationApiController.java') -Raw
if ($reservationController -notmatch 'updateReservationStatus') {
    $failures.Add('Missing admin reservation status update API.')
}
$securityConfig = Get-Content -LiteralPath (Join-Path $root 'src\main\java\com\marinboy\security\SecurityConfig.java') -Raw
$dbPathIndex = $securityConfig.IndexOf('"/api/db/**"')
$permitAllIndex = $securityConfig.IndexOf('.permitAll()', $dbPathIndex)
$nextMatcherIndex = $securityConfig.IndexOf('.requestMatchers', $dbPathIndex + 1)
if ($dbPathIndex -ge 0 -and $permitAllIndex -ge 0 -and ($nextMatcherIndex -lt 0 -or $permitAllIndex -lt $nextMatcherIndex)) {
    $failures.Add('/api/db/** is not restricted to administrators.')
}

Write-Host '[4/5] Production logging validation'
$properties = Get-Content -LiteralPath (Join-Path $root 'src\main\resources\application.properties') -Raw
if ($properties -match '(?im)^logging\.level\.com\.marinboy\s*=\s*debug\s*$') {
    $failures.Add('com.marinboy DEBUG logging may expose personal data.')
}

Write-Host '[5/5] Build and test'
Push-Location $root
try {
    if ($SkipTests) { mvn -q -DskipTests compile } else { mvn -q test }
    if ($LASTEXITCODE -ne 0) { $failures.Add('Maven build or test failed.') }
} finally {
    Pop-Location
}

if ($failures.Count -gt 0) {
    $failures | ForEach-Object { Write-Host "FAIL: $_" }
    exit 1
}

Write-Host 'Verification passed: no defects found.'
