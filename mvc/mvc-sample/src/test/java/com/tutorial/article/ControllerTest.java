package com.tutorial.article;

import com.tutorial.article.controller.Controller;
import com.tutorial.article.model.Model;
import com.tutorial.article.view.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Enclosed.class)
public class ControllerTest {

    private static final Controller underTest = Controller.INSTANCE;


    public static class GetAllTest {

        @Before
        public void setUp() throws Exception {
            DB.MODELS.put(1, new Model(1, "fake-1"));
            DB.MODELS.put(2, new Model(2, "fake-2"));
            DB.MODELS.put(3, new Model(3, "fake-3"));
        }

        @After
        public void tearDown() throws Exception {
            DB.MODELS.clear();
        }

        @Test
        public void givenEmptyView_whenCommandGetAll_ThenViewWillBeUpdatedWithAllModel() {
            var givenView = new View();

            underTest.getAll(givenView);

            assertNotNull(givenView.getId());
            assertEquals(3, givenView.getModels().length);

            givenView.print();
        }

    }

    public static class GetByIdTest {

        @Before
        public void setUp() throws Exception {
            DB.MODELS.put(1, new Model(1, "fake-1"));
        }

        @After
        public void tearDown() throws Exception {
            DB.MODELS.clear();
        }

        @Test
        public void givenEmptyViewAndId_whenCommandGetById_ThenViewWillBeUpdatedWithOneModel() {
            var givenView = new View();
            var givenId = 1;

            underTest.getById(givenView, givenId);

            assertNotNull(givenView.getId());
            assertEquals(1, givenView.getModels().length);

            givenView.print();
        }
    }
}
