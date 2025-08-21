package com.moya.domain.place.service;

import com.moya.domain.place.dto.PlaceDetailDto;
import com.moya.domain.place.entity.Place;
import com.moya.domain.place.entity.PlaceAudio;
import com.moya.domain.place.entity.PlaceI18n;
import com.moya.domain.place.repository.PlaceAudioRepository;
import com.moya.domain.place.repository.PlaceI18nRepository;
import com.moya.domain.place.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceDetailService {
    private final PlaceRepository placeRepository;
    private final PlaceI18nRepository placeI18nRepository;
    private final PlaceAudioRepository placeAudioRepository;

    public PlaceDetailService(PlaceRepository placeRepository, PlaceI18nRepository placeI18nRepository, PlaceAudioRepository placeAudioRepository) {
        this.placeRepository = placeRepository;
        this.placeI18nRepository = placeI18nRepository;
        this.placeAudioRepository = placeAudioRepository;
    }

    public PlaceDetailDto getPlaceDetail(Long placeNo, String locale) {
        Place place = placeRepository.findById(placeNo)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관광지입니다."));

        PlaceI18n placeI18n = placeI18nRepository
                .findByIdPlaceNoAndIdPlaceLocale(placeNo, locale)
                .orElseThrow(() -> new IllegalArgumentException("해당 언어의 설명이 존재하지 않습니다."));

        Optional<PlaceAudio> audioOpt = placeAudioRepository.findByPlaceNoAndLocale(placeNo, locale);

        String fullUrl = null;
        Integer duration = null;

        if (audioOpt.isPresent()) {
            PlaceAudio audio = audioOpt.get();
            fullUrl = audio.getAudioFullKey();
            duration = audio.getAudioDuration();
        }

        return new PlaceDetailDto(
                place.getBusinessHours(),
                placeI18n.getDescription(),
                fullUrl,
                duration
        );
    }

}

