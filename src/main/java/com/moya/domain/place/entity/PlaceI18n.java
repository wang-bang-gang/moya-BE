package com.moya.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_i18n")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceI18n {

    @EmbeddedId
    private PlaceI18nId id;

    @MapsId("placeNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_no")
    private Place place;

    @Column(name = "place_name", nullable = false, length = 200)
    private String name;

    @Column(name = "place_description", nullable = false, columnDefinition = "TEXT")
    private String description;

    // 편의 생성자
    public static PlaceI18n of(Long placeNo, String locale, Place place,
                                     String name, String description) {
        return PlaceI18n.builder()
                .id(new PlaceI18nId(placeNo, locale))
                .place(place)
                .name(name)
                .description(description)
                .build();
    }


}
