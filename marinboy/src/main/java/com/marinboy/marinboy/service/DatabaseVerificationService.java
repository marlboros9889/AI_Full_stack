package com.marinboy.marinboy.service;

import com.marinboy.marinboy.dto.DatabaseTimeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

// 현재 DB 시간 조회처럼 가벼운 데이터베이스 검증 쿼리를 실행합니다.
@Service
public class DatabaseVerificationService {

    private final JdbcTemplate jdbcTemplate;
    private final String validationQuery;
    private final String vendor;

    public DatabaseVerificationService(
            JdbcTemplate jdbcTemplate,
            @Value("${app.db.validation-query}") String validationQuery,
            @Value("${app.db.vendor}") String vendor) {
        this.jdbcTemplate = jdbcTemplate;
        this.validationQuery = validationQuery;
        this.vendor = vendor;
    }

    public DatabaseTimeResponse getDatabaseTime() {
        // 설정된 시간 조회 쿼리를 실행해 화면과 API에서 확인할 수 있게 반환합니다.
        Object value = jdbcTemplate.queryForObject(validationQuery, Object.class);
        return new DatabaseTimeResponse(vendor, validationQuery, value == null ? "" : value.toString());
    }
}
