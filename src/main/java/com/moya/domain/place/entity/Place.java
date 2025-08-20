package com.moya.domain.place.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "place")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_no")
    private Long placeNo;

    @Column(name = "is_published", nullable = false)
    private boolean published;

    @Column(name = "lat", nullable = false, precision = 9, scale = 6)
    private Double lat;

    @Column(name = "lng", nullable = false, precision = 12, scale = 8)
    private Double lng;

    @Column(name = "`like`")
    private Integer likeCount;

    @Column(name = "`comment`")
    private Integer commentCount;

    @Column(name = "business_hours", length = 10)
    private String businessHours;

    @Column(name = "image", length = 512)
    private String imageUrl;
}
