package com.tutorial.mvp.db;

import com.tutorial.mvp.model.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Collections.synchronizedMap;

public final class DB {

    public static final AtomicInteger MODEL_ID = new AtomicInteger(0);

    public static final AtomicReference<Map<Integer, Model>> MODELS = new AtomicReference<>(synchronizedMap(new HashMap<>()));

    private DB() {
    }
}
