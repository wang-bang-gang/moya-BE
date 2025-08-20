package com.moya.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "place_audio")
public class PlaceAudioEntity {
    @EmbeddedId
    private PlaceAudioId id;

    @MapsId("placeNo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_no", nullable = false)
    private PlaceEntity place;

    @Column(name = "audio_full_key", length = 512, nullable = false)
    private String audioFullKey;

    @Column(name = "audio_duration", nullable = false)
    private Integer audioDuration; // seconds

    public static PlaceAudioEntity of(Long placeNo, String locale, PlaceEntity place,
                                      String fullKey, Integer duration) {
        return PlaceAudioEntity.builder()
                .id(new PlaceAudioId(placeNo, locale))
                .place(place)
                .audioFullKey(fullKey)
                .audioDuration(duration)
                .build();
    }
}
