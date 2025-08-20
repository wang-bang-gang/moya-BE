package com.moya.domain.place.controller;

import com.moya.domain.place.dto.PlaceListItem;
import com.moya.domain.place.service.PlaceNearbyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceNearbyService placeNearbyService;

    /**
     * 사용자 위치 기준 근처 관광지 조회 API
     *
     * @param lat 사용자 위도
     * @param lng 사용자 경도
     * @param locale 언어 코드 (선택사항, 기본값: ko)
     * @return 15km 반경 내 관광지 리스트 (거리순 정렬)
     */
    @GetMapping("/nearby")
    public ResponseEntity<List<PlaceListItem>> getNearbyPlaces(
            @RequestParam("lat") Double lat,
            @RequestParam("lng") Double lng,
            @RequestParam(value = "locale", defaultValue = "ko") String locale) {

        try {
            List<PlaceListItem> places = placeNearbyService.getNearbyPlaces(lat, lng, locale);
            return ResponseEntity.ok(places);

        } catch (IllegalArgumentException e) {
            log.warn("잘못된 요청 파라미터: {}", e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("근처 관광지 조회 중 오류 발생", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}