package com.tutorial.eventstore.service;

import com.tutorial.eventstore.BaseTest;
import com.tutorial.eventstore.event.SampleCreated;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SampleStreamServiceTest extends BaseTest {

    private final SampleStreamService underTest = SampleStreamService.INSTANCE;


    @Nested
    class AppendTest {
        @AfterEach
        void tearDown() {
            underTest.deleteStream();
        }

        @Test
        void GivenEvent_WhenEventAppend_ShouldBeSucceeded() {
            var givenEvent = new SampleCreated(UUID.randomUUID(), "test");

            underTest.append(givenEvent);

            assertTrue(true);
        }
    }

    @Nested
    class GetTest {

        @BeforeEach
        void setUp() {
            var appendTest = new AppendTest();
            appendTest.GivenEvent_WhenEventAppend_ShouldBeSucceeded();
        }

        @AfterEach
        void tearDown() {
            underTest.deleteStream();
        }

        @Test
        void GivenEventType_WhenGetAll_ThenReturnListOfEvents() {
            var givenType = SampleCreated.class;

            var result = underTest.getAll(givenType);

            assertNotNull(result);
            assertFalse(result.isEmpty());
        }
    }
}
