package com.moya.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "place")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeNo;

    @Column(nullable = false)
    private Boolean isPublished;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lng;

    @Column(name = "like")
    private Integer likeCount;   // DB의 `like` 컬럼과 매핑

    private String businessHours;
    private String image;
}
