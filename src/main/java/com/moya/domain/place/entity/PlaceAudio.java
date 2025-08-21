package com.moya.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place_audio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceAudio {

    @EmbeddedId
    private PlaceAudioId id;

    @Column(name = "audio_preview_key", length = 512)
    private String audioPreviewKey;

    @Column(name = "audio_full_key", length = 512)
    private String audioFullKey;

    @Column(name = "audio_duration")
    private Integer audioDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_no", insertable = false, updatable = false)
    private Place place;

    public static PlaceAudio of(Long placeNo, String locale, Place place,
                                      String fullKey, Integer duration) {
        return PlaceAudio.builder()
                .id(new PlaceAudioId(placeNo, locale))
                .place(place)
                .audioFullKey(fullKey)
                .audioDuration(duration)
                .build();
    }
}
