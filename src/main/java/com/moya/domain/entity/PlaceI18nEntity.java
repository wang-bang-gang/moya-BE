package com.moya.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "place_i18n")
public class PlaceI18nEntity {
    @EmbeddedId
    private PlaceI18nId id;
    // place_no FK → place.place_no
    @MapsId("placeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_no", nullable = false)
    private PlaceEntity place;

    @Column(name = "place_name", length = 200, nullable = false)
    private String placeName;

    // TEXT 컬럼 매핑
    @Column(name = "place_description", columnDefinition = "TEXT", nullable = false)
    private String placeDescription;

    // 편의 생성자
    public static PlaceI18nEntity of(Long placeNo, String locale, PlaceEntity place,
                                     String placeName, String placeDescription) {
        return PlaceI18nEntity.builder()
                .id(new PlaceI18nId(placeNo, locale))
                .place(place)
                .placeName(placeName)
                .placeDescription(placeDescription)
                .build();
    }
}
