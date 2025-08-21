package com.moya.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class PlaceI18nId implements Serializable {
    @Column(name = "place_no", nullable = false)
    private Long placeNo;

    @Column(name = "place_locale", length = 10, nullable = false)
    private String placeLocale;
}
