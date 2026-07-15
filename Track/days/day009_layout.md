### 복습 문제 ###

1. 레이아웃잡는방법은? ( class    , block      , inline     )


2. box를 왼쪽, 오른쪽에 붙이는 방법은?

 .box1 {float: left;}
 .box2 {float: right;}

3. 다음박스를 왼쪽 오른쪽에 배치하려고 한다. 들어가야하는 코드는?
<style>
   .left{float:left;}     .right{float:right;}    .clear{clear: both;}
</style>
<div class="box">
  <div class="left">LEFT</div>
  <div class="right">RIGHT</div>
  <div class="clear">안따라갈려고</div>
</div>

4. 다음li를 왼쪽으로 붙이려고한다. 코드는?
<style>
  ul{float:left;}    li{float:left;}
</style>
<ul>
 <li>ONE </li><li>TWO</li><li>THREE</li>
</ul>