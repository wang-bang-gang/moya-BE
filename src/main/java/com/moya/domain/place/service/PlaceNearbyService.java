package com.moya.domain.place.service;

import com.moya.domain.place.dto.PlaceListItem;
import com.moya.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceNearbyService {

    private final PlaceRepository placeRepository;

    /**
     * 사용자 위치 기준 15km 반경 내 관광지를 거리순으로 조회
     *
     * @param userLat 사용자 위도
     * @param userLng 사용자 경도
     * @param locale 언어 코드 (ko, en 등)
     * @return 거리순으로 정렬된 관광지 리스트
     */
    public List<PlaceListItem> getNearbyPlaces(Double userLat, Double userLng, String locale) {
        log.info("근처 관광지 조회 - 사용자 위치: ({}, {}), 언어: {}", userLat, userLng, locale);

        // 입력 값 검증
        validateCoordinates(userLat, userLng);
        validateLocale(locale);

        List<PlaceListItem> places = placeRepository.findPlacesWithinRadius(userLat, userLng, locale);

        log.info("조회된 관광지 수: {}", places.size());
        return places;
    }

    /**
     * 좌표 유효성 검증
     */
    private void validateCoordinates(Double lat, Double lng) {
        if (lat == null || lng == null) {
            throw new IllegalArgumentException("위도와 경도는 필수 입력값입니다.");
        }

        if (lat < -90 || lat > 90) {
            throw new IllegalArgumentException("위도는 -90도에서 90도 사이의 값이어야 합니다.");
        }

        if (lng < -180 || lng > 180) {
            throw new IllegalArgumentException("경도는 -180도에서 180도 사이의 값이어야 합니다.");
        }
    }

    /**
     * 언어 코드 유효성 검증
     */
    private void validateLocale(String locale) {
        if (locale == null || locale.trim().isEmpty()) {
            throw new IllegalArgumentException("언어 코드는 필수 입력값입니다.");
        }
    }
}
