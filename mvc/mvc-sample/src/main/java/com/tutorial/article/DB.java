package com.tutorial.article;

import com.tutorial.article.model.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DB {
    public static final Map<Integer, Model> MODELS = Collections.synchronizedMap(new HashMap<>());
}
