package com.marinboy.marinboy.dto;

// 업로드한 이미지 파일을 화면에서 사용할 수 있도록 공개 URL로 알려주는 응답입니다.
public record ImageUploadResponse(String imageUrl) {
}
