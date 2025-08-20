package com.moya.domain.comment.repository;

import com.moya.domain.comment.dto.CommentListItem;
import com.moya.domain.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 관광지의 댓글을 최신순으로 조회
     *
     * @param placeNo 관광지 번호
     * @param locale 언어 코드
     * @return 최신순으로 정렬된 댓글 리스트
     */
    @Query("""
        SELECT new com.moya.domain.comment.dto.CommentListItem(
            c.commentId,
            c.placeNo,
            c.commentContent,
            c.commentTime,
            c.commentLocale
        )
        FROM Comment c
        WHERE c.placeNo = :placeNo
        AND c.commentLocale = :locale
        ORDER BY c.commentTime DESC
        """)
    List<CommentListItem> findCommentsByPlaceNoAndLocale(
            @Param("placeNo") Long placeNo,
            @Param("locale") String locale
    );

    /**
     * 댓글 조회 - 페이지네이션
     */
    @Query("""
        SELECT new com.moya.domain.comment.dto.CommentListItem(
            c.commentId,
            c.placeNo,
            c.commentContent,
            c.commentTime,
            c.commentLocale
        )
        FROM Comment c
        WHERE c.placeNo = :placeNo
        AND c.commentLocale = :locale
        ORDER BY c.commentTime DESC
        """)
    Page<CommentListItem> findCommentsByPlaceNoAndLocalePageable(
            @Param("placeNo") Long placeNo,
            @Param("locale") String locale,
            Pageable pageable
    );

    /**
     * 관광지 댓글 수 조회
     */
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.placeNo = :placeNo AND c.commentLocale = :locale")
    Long countCommentsByPlaceNoAndLocale(
            @Param("placeNo") Long placeNo,
            @Param("locale") String locale
    );
}