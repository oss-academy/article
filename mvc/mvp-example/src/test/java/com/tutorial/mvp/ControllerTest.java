package com.tutorial.mvp;

import com.tutorial.mvp.controller.Controller;
import com.tutorial.mvp.db.DB;
import com.tutorial.mvp.model.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ControllerTest {

    private static final Controller underTest = Controller.INSTANCE;

    public static class GetAllTest {

        @Before
        public void setUp() {
            int id1 = DB.MODEL_ID.incrementAndGet();
            DB.MODELS.get().put(id1, new Model(id1,"get-all-fake-1", 1));
            int id2 = DB.MODEL_ID.incrementAndGet();
            DB.MODELS.get().put(id2, new Model(id2,"get-all-fake-2", 2));
            int id3 = DB.MODEL_ID.incrementAndGet();
            DB.MODELS.get().put(id3, new Model(id3,"get-all-fake-3", 3));
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
            DB.MODEL_ID.set(0);
        }

        @Test
        public void givenEmptyView_whenCommandGetAll_ThenViewWillBeUpdatedWithAllModel() {
            var result = underTest.getAll();

            assertEquals(3, result.length);
        }

    }

    public static class GetByIdTest {

        @Before
        public void setUp() {
            int id = DB.MODEL_ID.incrementAndGet();
            DB.MODELS.get().put(id, new Model(id,"get-id-fake-1", 1));
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
            DB.MODEL_ID.set(0);
        }

        @Test
        public void givenEmptyViewAndId_whenCommandGetById_ThenViewWillBeUpdatedWithOneModel() {
            var givenId = 1;

            Optional<Model> result = underTest.getById(givenId);

            assertTrue(result.isPresent());
        }
    }

    public static class SaveTest {

        @Before
        public void setUp() {
            DB.MODELS.get().clear();
            DB.MODEL_ID.set(0);
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
            DB.MODEL_ID.set(0);
        }

        @Test
        public void givenModel_whenCommandSave_ThenDatabaseWillBeUpdatedWithNewModel() {
            var givenView = new TestConsoleView();
            givenView.setInput();

            int save = underTest.save(givenView.getInput());

            assertEquals(1, DB.MODELS.get().size());
        }
    }

    public static class DeleteTest {

        @Before
        public void setUp() {
            int id1 = DB.MODEL_ID.incrementAndGet();
            DB.MODELS.get().put(id1, new Model(id1,"delete-fake-1", 1));
            int id2 = DB.MODEL_ID.incrementAndGet();
            DB.MODELS.get().put(id2, new Model(id2,"delete-fake-2", 2));
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
        }

        @Test
        public void givenEmptyView_whenCommandGetAll_ThenViewWillBeUpdatedWithAllModel() {
            var givenId = 1;
            var givenCurrentSize = 2;

            var givenExpectedSize = givenCurrentSize - 1;

            underTest.deleteById(givenId);

            assertEquals(givenExpectedSize, DB.MODELS.get().size());
            assertFalse(DB.MODELS.get().containsKey(givenId));
        }

    }
}
