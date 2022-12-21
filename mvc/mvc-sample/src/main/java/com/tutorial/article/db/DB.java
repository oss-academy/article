package com.tutorial.article.db;

import com.tutorial.article.model.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class DB {

    public static final AtomicInteger MODEL_ID = new AtomicInteger(0);
    public static final AtomicReference<Map<Integer, Model>> MODELS = new AtomicReference<>(Collections.synchronizedMap(new HashMap<>()));

    private DB() {
    }
}
