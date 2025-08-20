package com.moya.domain.place.repository;

import com.moya.domain.place.dto.PlaceListItem;
import com.moya.domain.place.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = """


        select new com.moya.domain.place.dto.PlaceListItem(
           p.placeNo,
           coalesce(i18n.name, ''),
           coalesce(i18n.description, ''),
           p.lat, p.lng, p.imageUrl, p.published,
           p.likeCount, p.commentCount,
           cast(
               FUNCTION('acos',
                   FUNCTION('cos', FUNCTION('radians', :lat)) *
                   FUNCTION('cos', FUNCTION('radians', p.lat)) *
                   FUNCTION('cos', (FUNCTION('radians', p.lng) - FUNCTION('radians', :lng))) +
                   FUNCTION('sin', FUNCTION('radians', :lat)) *
                   FUNCTION('sin', FUNCTION('radians', p.lat))
               ) * 6371.0 as double
           )
        )
       
        from Place p
        left join p.i18ns i18n
               on i18n.id.placeLocale = :locale
        where (:publishedOnly = false or p.published = true)
          and p.lat between :minLat and :maxLat
          and p.lng between :minLng and :maxLng
          and (
              FUNCTION('acos',
                   FUNCTION('cos', FUNCTION('radians', :lat)) *
                   FUNCTION('cos', FUNCTION('radians', p.lat)) *
                   FUNCTION('cos', (FUNCTION('radians', p.lng) - FUNCTION('radians', :lng))) +
                   FUNCTION('sin', FUNCTION('radians', :lat)) *
                   FUNCTION('sin', FUNCTION('radians', p.lat))
              ) * 6371.0
          ) <= :radiusKm
            order by
          (
              FUNCTION('acos',
                   FUNCTION('cos', FUNCTION('radians', :lat)) *
                   FUNCTION('cos', FUNCTION('radians', p.lat)) *
                   FUNCTION('cos', (FUNCTION('radians', p.lng) - FUNCTION('radians', :lng))) +
                   FUNCTION('sin', FUNCTION('radians', :lat)) *
                   FUNCTION('sin', FUNCTION('radians', p.lat))
              ) * 6371.0
          ) asc
        
        """,
            countQuery = """
        select count(p)
        from Place p
        where (:publishedOnly = false or p.published = true)
          and p.lat between :minLat and :maxLat
          and p.lng between :minLng and :maxLng
          and (
              FUNCTION('acos',
                   FUNCTION('cos', FUNCTION('radians', :lat)) *
                   FUNCTION('cos', FUNCTION('radians', p.lat)) *
                   FUNCTION('cos', (FUNCTION('radians', p.lng) - FUNCTION('radians', :lng))) +
                   FUNCTION('sin', FUNCTION('radians', :lat)) *
                   FUNCTION('sin', FUNCTION('radians', p.lat))
              ) * 6371.0
          ) <= :radiusKm
        """)
    Page<PlaceListItem> findWithinRadiusOrderByDistance(
            @Param("locale") String locale,
            @Param("publishedOnly") boolean publishedOnly,
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("minLat") double minLat,
            @Param("maxLat") double maxLat,
            @Param("minLng") double minLng,
            @Param("maxLng") double maxLng,
            @Param("radiusKm") double radiusKm,
            Pageable pageable
    );
}


