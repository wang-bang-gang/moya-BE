package com.moya.domain.place.controller;

import com.moya.config.LocaleHolder;
import com.moya.domain.place.dto.PlaceDetailDto;
import com.moya.domain.place.dto.PlaceListItem;
import com.moya.domain.place.service.PlaceDetailService;
import com.moya.domain.place.service.PlaceNearbyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceNearbyService placeNearbyService;
    private final PlaceDetailService placeDetailService;

    // 수동으로 의존성 주입
    public PlaceController(PlaceNearbyService placeNearbyService,
                           PlaceDetailService placeDetailService) {
        this.placeNearbyService = placeNearbyService;
        this.placeDetailService = placeDetailService;
    }

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
            LocaleHolder.setLocale(locale);

            List<PlaceListItem> places = placeNearbyService.getNearbyPlaces(lat, lng, locale);
            return ResponseEntity.ok(places);

        } catch (IllegalArgumentException e) {
            log.warn("잘못된 요청 파라미터: {}", e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            log.error("근처 관광지 조회 중 오류 발생", e);
            return ResponseEntity.internalServerError().build();
        } finally {
            LocaleHolder.clear();
        }
    }

    // 관광지 상세 조회 API
    @GetMapping("/{placeNo}/detail")
    public PlaceDetailDto getPlaceDetail(@PathVariable Long placeNo,
                                         @RequestParam(defaultValue = "ko") String locale) {
        return placeDetailService.getPlaceDetail(placeNo, locale);
    }
}