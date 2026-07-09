package com.marinboy.marinboy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

// 미용실 시술 카탈로그를 정의하는 엔티티입니다.
@Entity
@Table(name = "salon_service_item")
public class SalonServiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    // 고객이 컷, 펌, 염색, 클리닉 등으로 시술을 빠르게 찾을 수 있게 분류합니다.
    @Column(nullable = false, length = 30)
    private String category;

    @Column(nullable = false)
    private int durationMinutes;

    // 화면에 금액을 표시할 수 있도록 가격을 별도 필드로 저장합니다.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 255)
    private String description;

    // 고객이 시술 결과 이미지를 보고 메뉴를 고를 수 있도록 대표 이미지 주소를 저장합니다.
    @Column(nullable = false, length = 500)
    private String imageUrl;

    // 대표 이미지 외에 고객이 참고할 수 있는 상세 이미지 주소들을 줄바꿈으로 저장합니다.
    @Column(length = 1500)
    private String additionalImageUrls;

    // 원장이 로그인 후 바로 보여줄 이달의 추천 TOP3 순위를 저장합니다.
    @Column
    private Integer topRank;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAdditionalImageUrls() {
        return additionalImageUrls;
    }

    public void setAdditionalImageUrls(String additionalImageUrls) {
        this.additionalImageUrls = additionalImageUrls;
    }

    public Integer getTopRank() {
        return topRank;
    }

    public void setTopRank(Integer topRank) {
        this.topRank = topRank;
    }
}
