import json
import requests
import pandas as pd
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import ServiceLog

def dashboard_view(request):
    spring_api_url = "http://localhost:8080/api/statistics/sync"

    try:
        response = requests.post(spring_api_url, timeout=3)
        if response.status_code == 200:
            print("Spring Boot 통계 동기화 성공")
        else:
            print(f"Spring Boot 응답 코드: {response.status_code}")
    except Exception as e:
        print(f"Spring Boot 서버 연결 실패: {e}")

    # DB 데이터 가져오기
    qs = ServiceLog.objects.all().values('date', 'category', 'visitor_count', 'sales_amount')

    if qs.exists():
        df = pd.DataFrame(list(qs))

        # ----------------------------------------------------
        # 📊 [Pandas 데이터 분석 수행]
        # ----------------------------------------------------

        # 1. 카테고리별 기본 집계 (합계)
        summary_df = df.groupby('category')[['visitor_count', 'sales_amount']].sum().reset_index()

        # 2. 비율/점유율 분석 (Share %)
        total_visitors = summary_df['visitor_count'].sum()
        if total_visitors > 0:
            summary_df['visitor_share'] = (summary_df['visitor_count'] / total_visitors * 100).round(1)
        else:
            summary_df['visitor_share'] = 0

        # 3. 주요 통계 지표 산출 (평균, 최대, 최소)
        stats_summary = {
            'avg_visitors': round(df['visitor_count'].mean(), 1),
            'max_visitors': int(df['visitor_count'].max()),
            'min_visitors': int(df['visitor_count'].min()),
            'total_count': int(df['visitor_count'].sum()),
        }

        # 4. 가장 실적이 높은 효자 카테고리 도출
        top_category_idx = summary_df['visitor_count'].idxmax()
        top_category = summary_df.loc[top_category_idx, 'category']

        # 5. 일자별 x 카테고리 트렌드 피벗 테이블 구성
        pivot_df = df.pivot_table(
            index='date', 
            columns='category', 
            values='visitor_count', 
            aggfunc='sum', 
            fill_value=0
        ).reset_index()

        # ----------------------------------------------------
        # 🎯 [템플릿 전달용 데이터 바인딩]
        # ----------------------------------------------------
        categories = summary_df['category'].tolist()
        visitors = summary_df['visitor_count'].tolist()
        sales = summary_df['sales_amount'].tolist()
        shares = summary_df['visitor_share'].tolist()
        trend_dates = pivot_df['date'].astype(str).tolist()

    else:
        categories, visitors, sales, shares, trend_dates = [], [], [], [], []
        stats_summary = {'avg_visitors': 0, 'max_visitors': 0, 'min_visitors': 0, 'total_count': 0}
        top_category = "데이터 없음"

    context = {
        # 기본 그래프용 데이터
        'categories': categories,
        'visitors': visitors,
        'sales': sales,
        'shares': shares,             # 카테고리별 점유율 (%)
        
        # 분석 요약 데이터
        'stats_summary': stats_summary, # 평균/최대/최소 통계
        'top_category': top_category,   # 최다 방문 카테고리
        'trend_dates': trend_dates,     # 일자별 트렌드 날짜
    }
    return render(request, 'analytics/dashboard.html', context)


@csrf_exempt
def api_receive_statistics(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            
            log_date = data.get('date')
            category = data.get('category', '커뮤니티')  
            count_val = data.get('count', 0)
            
            # 💡 [포트폴리오용 수정] update_or_create 사용
            # (date + category) 조건에 맞는 데이터가 있으면 최신 값으로 UPDATE, 없으면 CREATE
            ServiceLog.objects.update_or_create(
                date=log_date,
                category=category,
                defaults={
                    'visitor_count': count_val,
                    'sales_amount': 0
                }
            )
            
            return JsonResponse({'status': 'success', 'message': '통계 데이터 갱신 완료!'})
        except Exception as e:
            return JsonResponse({'status': 'error', 'message': str(e)}, status=400)
            
    return JsonResponse({'status': 'fail', 'message': 'POST 요청만 지원합니다.'}, status=405)




# import pandas as pd
# from django.shortcuts import render
# from .models import ServiceLog

# def dashboard_view(request):
#     qs = ServiceLog.objects.all().values( 'date' , 'category' , 'visitor_count' , 'sales_amount')

#     # 1. DB 전체 데이터를 QuerySet으로 추출 후 Pandas DataFrame으로 변환
#     if  qs.exists():
#         df = pd.DataFrame(list(qs))
#         # 2. Pandas 연산: 카테고리별 방문자 수 및 매출액 합계 집계
#         #               1) 그룹핑    합계    다시정렬
#         summary_df = df.groupby('category')[['visitor_count' , 'sales_amount']].sum().reset_index()
#         # 3. 템플릿 전달용 순수 파이썬 리스트 추출
#         categories = summary_df['category'].tolist()
#         visitors   = summary_df['visitor_count'].tolist()
#         sales      = summary_df['sales_amount'].tolist()
#     else:
#         categories , visitors , sales = [],[],[]
#     # 4. 템플릿(html)전달할때 바인딩객체 ( Spring - Model , ModelAndView)
#     context = {
#         'categories': categories,
#         'visitors': visitors,
#         'sales': sales,
#     }
#     return render(request, 'analytics/dashboard.html', context)
