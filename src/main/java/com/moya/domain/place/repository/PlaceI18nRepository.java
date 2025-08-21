package com.moya.domain.place.repository;

import com.moya.domain.place.entity.PlaceI18n;
import com.moya.domain.place.entity.PlaceI18nId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceI18nRepository extends JpaRepository<PlaceI18n, PlaceI18nId> {

    // 파생 쿼리: id.placeNo, id.placeLocale을 통해 복합키로 조회
    Optional<PlaceI18n> findByIdPlaceNoAndIdPlaceLocale(Long placeNo, String placeLocale);
}
