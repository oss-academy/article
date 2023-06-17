package com.tutorial.eventstore.service;

import com.tutorial.eventstore.event.*;
import com.tutorial.eventstore.stream.EventStream;
import com.tutorial.eventstore.stream.SampleEventStream;
import com.tutorial.eventstore.testutil.TestEventStoreHelper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static com.tutorial.eventstore.service.SampleStreamServiceTest.EventFactory.createFakeSampleEvents;
import static com.tutorial.eventstore.util.StreamUtils.generateId;
import static org.junit.jupiter.api.Assertions.*;

class SampleStreamServiceTest {

    private static final TestEventStoreHelper eventStore = new TestEventStoreHelper();

    private final SampleStreamService underTest = SampleStreamService.getInstance();

    static class EventFactory {

        public static List<Event<Object>> createFakeSampleEvents(String streamId) {
            return List.of(
                    new SampleCreated(streamId, UUID.randomUUID(), "fake created data"),
                    new SampleProcessed(streamId, UUID.randomUUID(), "fake processed data"),
                    new SampleClosed(streamId, UUID.randomUUID(), "fake closed data"),
                    new SampleRemoved(streamId, UUID.randomUUID(), "fake removed data")
            );
        }
    }
    static class InvalidEventStream implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(null, NullPointerException.class, "eventStream should not be null"),
                    Arguments.of(new SampleEventStream(null, List.of()), NullPointerException.class, "stream Id should not be null"),
                    Arguments.of(new SampleEventStream("", List.of()), IllegalArgumentException.class, "stream Id should not be empty"),
                    Arguments.of(new SampleEventStream(generateId(SampleEventStream.class.getSimpleName()), null), NullPointerException.class, "event list should not be null"),
                    Arguments.of(new SampleEventStream(generateId(SampleEventStream.class.getSimpleName()), List.of()), IllegalArgumentException.class, "event list should not be empty")
            );
        }
    }

    @BeforeAll
    static void setup() {
        eventStore.start();
    }

    @AfterAll
    static void tearDown() {
        eventStore.shutdown();
    }

    @Nested
    class AppendTest {

        @Test
        @DisplayName("should add events to the stream successful")
        void shouldAppendEventsToStream() {
            var givenStreamId = generateId(SampleEventStream.class.getSimpleName());
            var givenEvents = createFakeSampleEvents(givenStreamId);
            var givenStream = new SampleEventStream(givenStreamId, givenEvents);

            var actual = underTest.append(givenStream);

            assertNotNull(actual);
            assertTrue(actual.isPresent());
        }

        @ParameterizedTest(name = "[{index}]: {2}")
        @ArgumentsSource(InvalidEventStream.class)
        @DisplayName("should throw an exception if inputs are not valid")
        void shouldThrowException_IfInputsAreNotValid(EventStream<Object, Event<Object>> givenEventStream,
                                                     Class<? extends Throwable> expectedException,
                                                     String expectedExceptionMessage) {

            var actual = assertThrows(expectedException, () -> underTest.append(givenEventStream));

            assertNotNull(actual);
            assertEquals(expectedExceptionMessage, actual.getMessage());
        }

    }

    @Nested
    class ReadTest {

        private final String givenStreamId = generateId(SampleEventStream.class.getSimpleName());

        @BeforeEach
        void setup() {
            var actual = underTest.append(new SampleEventStream(givenStreamId, createFakeSampleEvents(givenStreamId)));
            assertNotNull(actual);
            assertTrue(actual.isPresent());
        }

        @Test
        @DisplayName("return list of events of a stream")
        void shouldReturnListOfEvents() {
            var actual = underTest.read(givenStreamId);

            assertNotNull(actual);
            assertTrue(actual.isPresent());
            actual.ifPresent(stream -> assertEquals(4, stream.getEvents().size()));
        }

        @Test
        @DisplayName("should return NullPointerException if stream Id is null")
        void shouldThrowNullPointerException_IfStreamIdIsNull() {
            var actual = assertThrows(NullPointerException.class, () -> underTest.read(null));

            assertNotNull(actual);
            assertEquals("stream Id should not be null", actual.getMessage());
        }

        @Test
        @DisplayName("should return IllegalArgumentException if stream Id is null")
        void shouldThrowIllegalArgumentException_IfStreamIdIsEmpty() {
            var actual = assertThrows(IllegalArgumentException.class, () -> underTest.read(""));

            assertNotNull(actual);
            assertEquals("stream Id should not be empty", actual.getMessage());
        }
    }

    @Nested
    class DeleteTest {

        private final SampleStreamService underTest = SampleStreamService.getInstance();

        private final String givenStreamId = generateId(SampleEventStream.class.getSimpleName());

        @BeforeEach
        void setup() {
            var actual = underTest.append(new SampleEventStream(givenStreamId, createFakeSampleEvents(givenStreamId)));
            assertNotNull(actual);
            assertTrue(actual.isPresent());
        }

        @Test
        @DisplayName("should delete a stream by stream Id")
        void ShouldDeleteStreamByStreamId() {
            try {
               underTest.delete(givenStreamId);
            } catch (Exception e) {
                fail("deleting sample stream failed", e);
            }
        }

        @Test
        @DisplayName("should return NullPointerException if stream Id is null")
        void shouldThrowNullPointerException_IfStreamIdIsNull() {
            var actual = assertThrows(NullPointerException.class, () -> underTest.delete(null));

            assertNotNull(actual);
            assertEquals("stream Id should not be null", actual.getMessage());
        }

        @Test
        @DisplayName("should return IllegalArgumentException if stream Id is null")
        void shouldThrowIllegalArgumentException_IfStreamIdIsEmpty() {
            var actual = assertThrows(IllegalArgumentException.class, () -> underTest.delete(""));

            assertNotNull(actual);
            assertEquals("stream Id should not be empty", actual.getMessage());
        }
    }

}
