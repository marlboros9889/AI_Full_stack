




package com.thejoa703.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejoa703.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsSyncService {

    private final PostRepository postRepository;

    public void sendRealStatsToDjango() {
        String djangoUrl = "http://localhost:8000/dashboard/api/statistics/";
        
        // RestClient 대신 가장 안정적인 RestTemplate 사용
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // 1. 오라클 DB 데이터 집계
        long totalPosts = postRepository.count();

        Map<String, Object> payload = new HashMap<>();
        payload.put("date", LocalDate.now().toString());
        payload.put("category", "커뮤니티 게시글");
        payload.put("count", (int) totalPosts);

        try {
            // 2. Map을 JSON 문자열로 명확히 변환
            String jsonBody = objectMapper.writeValueAsString(payload);

            // 3. HTTP Header 설정 (Content-Type: application/json)
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            // 4. Django로 POST 요청 전송
            restTemplate.postForEntity(djangoUrl, entity, String.class);

            System.out.println("✅ Django 통계 전송 성공!");
        } catch (Exception e) {
            System.out.println("❌ Django 통계 전송 실패: " + e.getMessage());
        }
    }
}