-- V1__init.sql
CREATE DATABASE IF NOT EXISTS moya CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE moya;

-- 1) place (관광지_공통)
CREATE TABLE IF NOT EXISTS place (
                                     place_no        BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     is_published    BOOLEAN       NOT NULL,
                                     lat             DECIMAL(9,6)  NOT NULL,
    lng             DECIMAL(12,8) NOT NULL,
    `like`          INT NULL,
    `comment`       INT NULL,
    business_hours  VARCHAR(10) NULL,
    image           VARCHAR(512) NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2) place_i18n (관광지_언어별)
CREATE TABLE IF NOT EXISTS place_i18n (
                                          place_no          BIGINT       NOT NULL,
                                          place_locale      VARCHAR(10)  NOT NULL,
    place_name        VARCHAR(200) NOT NULL,
    place_description TEXT         NOT NULL,           -- 긴 설명 고려해 TEXT로 설정했습니다
    PRIMARY KEY (place_no, place_locale),
    CONSTRAINT fk_place_i18n_place
    FOREIGN KEY (place_no) REFERENCES place(place_no) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) place_audio (관광지_오디오)
CREATE TABLE IF NOT EXISTS place_audio (
                                           place_no          BIGINT       NOT NULL,
                                           audio_locale      VARCHAR(10)  NOT NULL,
    audio_preview_key VARCHAR(512) NOT NULL,
    audio_full_key    VARCHAR(512) NOT NULL,
    audio_duration    INT          NOT NULL,
    PRIMARY KEY (place_no, audio_locale),
    CONSTRAINT fk_place_audio_place
    FOREIGN KEY (place_no) REFERENCES place(place_no) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) comment (댓글)
CREATE TABLE IF NOT EXISTS comment (
                                       comment_id      BIGINT AUTO_INCREMENT PRIMARY KEY, -- ★ PK는 별도 ID
                                       place_no        BIGINT       NOT NULL,             -- ★ FK (AUTO_INCREMENT 아님)
                                       comment_locale  VARCHAR(10)  NOT NULL,
    comment_content VARCHAR(1000) NOT NULL,
    comment_time    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_place
    FOREIGN KEY (place_no) REFERENCES place(place_no) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 인덱스 (조회 패턴 대비)
CREATE INDEX idx_comment_place_locale ON comment(place_no, comment_locale);
-- 위치 기반 검색 고려 시 place에 추가 인덱스
CREATE INDEX idx_place_lat_lng ON place(lat, lng);
