package com.example.benchmark;

import android.content.Context;

import androidx.benchmark.BenchmarkState;
import androidx.benchmark.junit4.BenchmarkRule;
import androidx.test.core.app.ApplicationProvider;

import com.example.benchmark.json_parse.models.N_Deck;
import com.example.benchmark.json_parse.old.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TagsBenchmark {


    @Rule
    public BenchmarkRule mBenchmarkRule = new BenchmarkRule();

    private final Context context = ApplicationProvider.getApplicationContext();
    private String tagsJson;


    @Before
    public void setup() throws IOException {
        try (InputStream open = context.getAssets().open("tags.json")) {
            tagsJson = new BufferedReader(new InputStreamReader(open))
                    .lines().collect(Collectors.joining("\n"));
        }
    }

    @Test
    public void old() throws IOException {
        Map<String, Integer> mTags = null;

        final BenchmarkState state = mBenchmarkRule.getState();

        while (state.keepRunning()) {
            JSONObject tags = new JSONObject(tagsJson);
            mTags = new HashMap<>();
            for (String t : tags) {
                mTags.put(t, tags.getInt(t));
            }
        }
        mTags.clear();
    }

    @Test
    public void jackson() throws JsonProcessingException {
        Map<String, Integer> mTags = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        ObjectMapper objectMapper = new ObjectMapper();

        while (state.keepRunning()) {
            mTags = objectMapper.readValue(tagsJson, new TypeReference<Map<String, Integer>>() {
            });
        }

        mTags.clear();
    }

    @Test
    public void gson() throws JsonProcessingException {
        Map<String, Integer> mTags = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        Gson gson = new Gson();

        while (state.keepRunning()) {
            mTags = gson.fromJson(tagsJson, new TypeToken<Map<String, Integer>>() {
            }.getType());
        }

        mTags.clear();
    }

    @Test
    public void jackson_streaming() throws IOException {
        Map<String, Integer> mTags = null;

        final BenchmarkState state = mBenchmarkRule.getState();

        while (state.keepRunning()) {
            state.pauseTiming();
            final JsonParser jp = new JsonFactory().createParser(tagsJson);
            state.resumeTiming();

            mTags = new HashMap<>();
            if (jp.nextToken() != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected '{'");
            }
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                final String key = jp.getCurrentName();
                jp.nextToken();
                final int value = jp.getValueAsInt();
                mTags.put(key, value);
            }
            state.pauseTiming();
            jp.close();
            state.resumeTiming();
        }
        mTags.clear();
    }
}
