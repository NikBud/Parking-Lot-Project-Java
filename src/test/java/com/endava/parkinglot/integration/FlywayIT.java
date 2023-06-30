package com.endava.parkinglot.integration;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class FlywayIT {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void checkUsersTableTableExists() {
        boolean tableExists = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_table')",
                Boolean.class);
        assertTrue(tableExists,"Table wasn't found");
    }
    @Test
    void checkParkingLotTableExists() {
        boolean tableExists = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'parking_lot')",
                Boolean.class);
        assertTrue(tableExists,"Table wasn't found");
    }

    @Test
    void checkParkingLevelTableExists() {
        boolean tableExists = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'parking_level')",
                Boolean.class);
        assertTrue(tableExists,"Table wasn't found");
    }

    @Test
    void checkParkingSpaceTableExists() {
        boolean tableExists = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'parking_space')",
                Boolean.class);
        assertTrue(tableExists,"Table wasn't found");
    }


}
