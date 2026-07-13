package com.marinboy.dao;

import com.marinboy.dto.ServiceDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// 시술 메뉴와 이미지 정보를 조회하는 MyBatis DAO입니다.
@Mapper
public interface SalonServiceDao {

    // 고객 화면에 표시할 전체 시술 메뉴를 조회합니다.
    List<ServiceDto> findAllServices();

    // 시술별 대표 이미지와 추가 이미지를 조회합니다.
    List<ServiceDto> findAllServiceImages();

    // 예약 가능 시간 계산에 사용할 시술 소요 시간을 조회합니다.
    Integer findDurationMinutesById(@Param("serviceId") Long serviceId);
    int insertService(@Param("name") String name, @Param("category") String category, @Param("durationMinutes") int durationMinutes, @Param("price") int price, @Param("description") String description);
    int updateService(@Param("id") Long id, @Param("name") String name, @Param("category") String category, @Param("durationMinutes") int durationMinutes, @Param("price") int price, @Param("description") String description);
    int deleteService(@Param("id") Long id);
}
