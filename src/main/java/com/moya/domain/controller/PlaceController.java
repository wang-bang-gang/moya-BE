package com.moya.domain.controller;

import com.moya.domain.dto.PlaceDetailDto;
import com.moya.domain.service.PlaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    // 관광지 상세 조회 API
    @GetMapping("/{placeNo}/detail")
    public PlaceDetailDto getPlaceDetail(@PathVariable Long placeNo,
                                         @RequestParam(defaultValue = "ko") String locale) {
        return placeService.getPlaceDetail(placeNo, locale);
    }
}
