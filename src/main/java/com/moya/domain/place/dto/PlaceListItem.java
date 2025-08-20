package com.moya.domain.place.dto;

public record PlaceListItem(
        Long id,
        String name,
        String description,
        Double lat,
        Double lng,
        String imageUrl,
        boolean published,
        Integer likeCount,
        Integer commentCount,
        Number distance
) {}
