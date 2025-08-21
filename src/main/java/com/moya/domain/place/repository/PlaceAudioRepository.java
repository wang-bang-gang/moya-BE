package com.moya.domain.place.repository;

import com.moya.domain.place.entity.PlaceAudio;
import com.moya.domain.place.entity.PlaceAudioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceAudioRepository extends JpaRepository<PlaceAudio, PlaceAudioId> {

    @Query("SELECT a FROM PlaceAudio a " +
            "WHERE a.id.placeNo = :placeNo AND a.id.audioLocale = :locale")

    Optional<PlaceAudio> findByPlaceNoAndLocale(@Param("placeNo") Long placeNo,
                                                      @Param("locale") String locale);
}