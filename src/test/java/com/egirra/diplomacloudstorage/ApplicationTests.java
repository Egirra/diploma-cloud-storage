package com.egirra.diplomacloudstorage;

import com.egirra.diplomacloudstorage.repository.FileRepository;
import com.egirra.diplomacloudstorage.security.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FileRepository fileRepository;

    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("postgres");

    @Test
    void contextLoadDatabase() {
        System.out.println(database.getJdbcUrl());
        Assertions.assertTrue(database.isRunning());
    }

    @Test
    void testFindUser() {
        String expectedUserName = "Ivan";
        String reality = String.valueOf(userRepository.findByUserName("Ivan"));
        Assertions.assertEquals(expectedUserName, reality);
    }

    @Test
    void testFindFile() {
        String expected = "File1";
        String reality = String.valueOf(fileRepository.findByFileName("File1"));
        Assertions.assertEquals(expected, reality);
    }
}