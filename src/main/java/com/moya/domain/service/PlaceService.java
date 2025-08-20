package com.moya.domain.service;

import com.moya.domain.dto.PlaceDetailDto;
import com.moya.domain.entity.PlaceAudioEntity;
import com.moya.domain.entity.PlaceEntity;
import com.moya.domain.entity.PlaceI18nEntity;
import com.moya.domain.repository.PlaceAudioRepository;
import com.moya.domain.repository.PlaceI18nRepository;
import com.moya.domain.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceI18nRepository placeI18nRepository;
    private final PlaceAudioRepository placeAudioRepository;

    public PlaceService(PlaceRepository placeRepository, PlaceI18nRepository placeI18nRepository, PlaceAudioRepository placeAudioRepository) {
        this.placeRepository = placeRepository;
        this.placeI18nRepository = placeI18nRepository;
        this.placeAudioRepository = placeAudioRepository;
    }

    public PlaceDetailDto getPlaceDetail(Long placeNo, String locale) {
        PlaceEntity place = placeRepository.findById(placeNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관광지입니다."));

        PlaceI18nEntity placeI18n = placeI18nRepository
                .findByIdPlaceNoAndIdPlaceLocale(placeNo, locale)
                .orElseThrow(() -> new IllegalArgumentException("해당 언어의 설명이 존재하지 않습니다."));

        Optional<PlaceAudioEntity> audioOpt = placeAudioRepository.findByPlaceNoAndLocale(placeNo, locale);

        String fullUrl = null;
        Integer duration = null;

        if (audioOpt.isPresent()) {
            PlaceAudioEntity audio = audioOpt.get();
            fullUrl = audio.getAudioFullKey();
            duration = audio.getAudioDuration();
        }

        return new PlaceDetailDto(
                place.getBusinessHours(),
                placeI18n.getPlaceDescription(),
                fullUrl,
                duration
        );
    }

}

