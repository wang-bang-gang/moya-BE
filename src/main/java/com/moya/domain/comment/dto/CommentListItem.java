package com.moya.domain.comment.dto;

import java.time.LocalDateTime;

public record CommentListItem(
        Long commentId,
        Long placeNo,
        String commentContent,
        LocalDateTime commentTime,
        String commentLocale
) {}
