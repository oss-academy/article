package com.tutorial.article.view;

import com.tutorial.article.model.Model;

import java.util.List;

public interface Cacheable {
    List<Model> cache();

    void cache(Model... models);

    void clear();

    void viewCache();
}
