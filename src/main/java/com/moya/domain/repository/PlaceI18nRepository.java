package com.moya.domain.repository;

import com.moya.domain.entity.PlaceI18nEntity;
import com.moya.domain.entity.PlaceI18nId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceI18nRepository extends JpaRepository<PlaceI18nEntity, PlaceI18nId> {

    // 파생 쿼리: id.placeNo, id.placeLocale을 통해 복합키로 조회
    Optional<PlaceI18nEntity> findByIdPlaceNoAndIdPlaceLocale(Long placeNo, String placeLocale);
}
