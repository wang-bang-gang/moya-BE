package com.moya.domain.place.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlaceDetailDto {
    private String businessHours;   // 운영시간
    private String description;     // 관광지 설명 (언어별)
    private String audioFullUrl;    // 풀버전 도슨트 URL
    private Integer audioDuration;  // 전체 길이(초)
}
