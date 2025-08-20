package com.moya.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "place")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_no")
    private Long placeNo;

    @Column(name = "is_published", nullable = false)
    private boolean published;

    @Column(name = "lat", nullable = false)
    private Double lat;

    @Column(name = "lng", nullable = false)
    private Double lng;

    @Column(name = "`like`")
    private Integer likeCount;

    @Column(name = "`comment`")
    private Integer commentCount;

    @Column(name = "business_hours", length = 10)
    private String businessHours;

    @Column(name = "image", length = 512)
    private String imageUrl;

    // i18n 연관관계 (공통 정보 <-> 다국어 )
    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<PlaceI18n> i18ns = new ArrayList<>();
}

