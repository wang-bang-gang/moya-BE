package com.moya.domain.repository;

import com.moya.domain.entity.PlaceAudioEntity;
import com.moya.domain.entity.PlaceAudioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceAudioRepository extends JpaRepository<PlaceAudioEntity, PlaceAudioId> {

    @Query("SELECT a FROM PlaceAudioEntity a " +
            "WHERE a.id.placeNo = :placeNo AND a.id.audioLocale = :locale")

    Optional<PlaceAudioEntity> findByPlaceNoAndLocale(@Param("placeNo") Long placeNo,
                                                      @Param("locale") String locale);
}