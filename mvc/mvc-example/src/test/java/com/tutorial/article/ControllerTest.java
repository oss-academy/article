package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.db.DB;
import com.tutorial.article.model.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
            var givenView = new TestConsoleView();

            underTest.getAll(givenView);

            assertEquals(3, givenView.getModel().length);
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
            var givenView = new TestConsoleView();
            var givenId = 1;

            underTest.getById(givenView, givenId);

            assertEquals(1, givenView.getModel().length);
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

            underTest.save(givenView);

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
            var givenView = new TestConsoleView();
            var givenId = 1;
            var givenCurrentSize = 2;

            var givenExpectedSize = givenCurrentSize - 1;

            underTest.deleteById(givenView, givenId);

            assertEquals(givenExpectedSize, DB.MODELS.get().size());
            assertFalse(DB.MODELS.get().containsKey(givenId));
        }

    }
}
