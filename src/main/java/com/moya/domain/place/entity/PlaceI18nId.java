package com.moya.domain.place.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class PlaceI18nId implements Serializable {
    private Long placeNo;
    private String placeLocale;
}

