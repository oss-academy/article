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

@RunWith(Enclosed.class)
public class ControllerTest {

    private static final Controller underTest = Controller.INSTANCE;

    public static class GetAllTest {

        @Before
        public void setUp() {
            DB.MODELS.put(1, new Model(1, "get-all-fake-1"));
            DB.MODELS.put(2, new Model(2, "get-all-fake-2"));
            DB.MODELS.put(3, new Model(3, "get-all-fake-3"));
        }

        @After
        public void tearDown() {
            DB.MODELS.clear();
        }

        @Test
        public void givenEmptyView_whenCommandGetAll_ThenViewWillBeUpdatedWithAllModel() {
            var givenView = new ConsoleView();

            underTest.getAll(givenView);

            assertEquals(3, givenView.getModels().length);
            givenView.view();
        }

    }

    public static class GetByIdTest {

        @Before
        public void setUp() {
            DB.MODELS.put(1, new Model(1, "get-id-fake-1"));
        }

        @After
        public void tearDown() {
            DB.MODELS.clear();
        }

        @Test
        public void givenEmptyViewAndId_whenCommandGetById_ThenViewWillBeUpdatedWithOneModel() {
            var givenView = new ConsoleView();
            var givenId = 1;

            underTest.getById(givenView, givenId);

            assertEquals(1, givenView.getModels().length);
            givenView.view();
        }
    }

    public static class SaveTest {

        @Before
        public void setUp() {
            DB.MODELS.clear();
        }

        @After
        public void tearDown() {
            DB.MODELS.clear();
        }

        @Test
        public void givenModel_whenCommandSave_ThenDatabaseWillBeUpdatedWithNewModel() {
            var givenView = new ConsoleView();
            givenView.update(new Model(1, "save-fake-1"));

            underTest.save(givenView);

            assertEquals(1, DB.MODELS.size());
            givenView.view();
        }
    }
}
