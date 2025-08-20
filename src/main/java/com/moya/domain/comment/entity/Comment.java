package com.moya.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "place_no", nullable = false)
    private Long placeNo;

    @Column(name = "comment_locale", nullable = false, length = 10)
    private String commentLocale;

    @Column(name = "comment_content", nullable = false, length = 1000)
    private String commentContent;

    @Column(name = "comment_time", nullable = false)
    private LocalDateTime commentTime;
}
