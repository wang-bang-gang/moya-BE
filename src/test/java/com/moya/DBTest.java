package com.moya;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DBTest {

    private static final Logger logger = LoggerFactory.getLogger(DBTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testDatabaseConnection() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertEquals(Integer.valueOf(1), result);

        String version = jdbcTemplate.queryForObject("SELECT VERSION()", String.class);
        logger.info("MySQL Version: {}", version);

        String database = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
        logger.info("Current Database: {}", database);
    }
}