package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.db.DB;
import com.tutorial.article.model.Model;
import com.tutorial.article.view.ConsoleView;
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
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), new Model(1, "get-all-fake-1"));
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), new Model(2, "get-all-fake-2"));
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), new Model(3, "get-all-fake-3"));
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
            DB.MODEL_ID.set(0);
        }

        @Test
        public void givenEmptyView_whenCommandGetAll_ThenViewWillBeUpdatedWithAllModel() {
            var givenView = new ConsoleView();

            underTest.getAll(givenView);

            assertEquals(3, givenView.models().length);
        }

    }

    public static class GetByIdTest {

        @Before
        public void setUp() {
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), new Model(1, "get-id-fake-1"));
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
            DB.MODEL_ID.set(0);
        }

        @Test
        public void givenEmptyViewAndId_whenCommandGetById_ThenViewWillBeUpdatedWithOneModel() {
            var givenView = new ConsoleView();
            var givenId = 1;

            underTest.getById(givenView, givenId);

            assertEquals(1, givenView.models().length);
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
            var givenView = new ConsoleView();
            givenView.models(new Model(1, "save-fake-1"));

            underTest.save(givenView);

            assertEquals(1, DB.MODELS.get().size());
        }
    }

    public static class DeleteTest {

        @Before
        public void setUp() {
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), new Model(1, "delete-fake-1"));
            DB.MODELS.get().put(DB.MODEL_ID.incrementAndGet(), new Model(2, "delete-fake-2"));
        }

        @After
        public void tearDown() {
            DB.MODELS.get().clear();
        }

        @Test
        public void givenEmptyView_whenCommandGetAll_ThenViewWillBeUpdatedWithAllModel() {
            var givenView = new ConsoleView();
            var givenId = 1;
            var givenCurrentSize = 2;

            var givenExpectedSize = givenCurrentSize - 1;

            underTest.deleteById(givenView, givenId);

            assertEquals(givenExpectedSize, DB.MODELS.get().size());
            assertFalse(DB.MODELS.get().containsKey(givenId));
        }

    }
}
