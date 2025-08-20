package com.moya.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_i18n")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
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
}
