package com.moya.domain.place.repository;

import com.moya.domain.place.dto.PlaceListItem;
import com.moya.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    /**
     * 사용자 위치 기준 15km 반경 내 관광지를 거리순으로 조회
     * Haversine 공식을 사용하여 거리 계산
     *
     * @param userLat 사용자 위도
     * @param userLng 사용자 경도
     * @param locale 언어 코드 (ko, en 등)
     * @return 거리순으로 정렬된 관광지 리스트
     */
    @Query("""
        SELECT new com.moya.domain.place.dto.PlaceListItem(
            p.placeNo,
            i.name,
            i.description,
            p.lat,
            p.lng,
            p.imageUrl,
            p.published,
            p.likeCount,
            p.commentCount,
            (6371 * ACOS(
                COS(RADIANS(:userLat)) * COS(RADIANS(p.lat)) * 
                COS(RADIANS(p.lng) - RADIANS(:userLng)) + 
                SIN(RADIANS(:userLat)) * SIN(RADIANS(p.lat))
            ))
        )
        FROM Place p 
        JOIN p.i18ns i 
        WHERE p.published = true 
        AND i.id.placeLocale = :locale
        AND (6371 * ACOS(
            COS(RADIANS(:userLat)) * COS(RADIANS(p.lat)) * 
            COS(RADIANS(p.lng) - RADIANS(:userLng)) + 
            SIN(RADIANS(:userLat)) * SIN(RADIANS(p.lat))
        )) <= 15
        ORDER BY (6371 * ACOS(
            COS(RADIANS(:userLat)) * COS(RADIANS(p.lat)) * 
            COS(RADIANS(p.lng) - RADIANS(:userLng)) + 
            SIN(RADIANS(:userLat)) * SIN(RADIANS(p.lat))
        )) ASC
        """)
    List<PlaceListItem> findPlacesWithinRadius(
            @Param("userLat") Double userLat,
            @Param("userLng") Double userLng,
            @Param("locale") String locale
    );
}
