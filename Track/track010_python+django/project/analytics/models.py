from django.db import models

class ServiceLog(models.Model):
    # date, category, visitor_count, sales_amount 필드 정의
    # DateField : YYYY-MM-DD 날짜필드
    date=models.DateField(verbose_name="날짜")

    category=models.CharField(max_length=50, verbose_name="카테고리")

    visitor_count=models.IntegerField(default=0, verbose_name="방문자수") 
    sales_amount=models.IntegerField(default=0, verbose_name="매출액") 
    # Java toString 해당기능
    def __str__(self):
        return f"[{self.date}] {self.category} 로그"

    class Meta:
        # 같은 날짜와 카테고리 통계는 한 건만 저장해 새로고침 중복을 막습니다.
        constraints = [
            models.UniqueConstraint(
                fields=["date", "category"],
                name="unique_service_log_date_category",
            )
        ]
