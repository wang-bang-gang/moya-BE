package com.moya.domain.place.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlaceAudioId implements Serializable {
    @Column(name = "place_no")
    private Long placeNo;

    @Column(name = "audio_locale")
    private String audioLocale;
}