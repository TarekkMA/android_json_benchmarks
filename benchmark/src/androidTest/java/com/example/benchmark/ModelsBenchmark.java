package com.example.benchmark;

import android.content.Context;

import androidx.benchmark.junit4.BenchmarkRule;
import androidx.benchmark.BenchmarkState;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.benchmark.json_parse.models.N_Model;
import com.example.benchmark.json_parse.old.JSONArray;
import com.example.benchmark.json_parse.old.JSONObject;
import com.example.benchmark.json_parse.old.Model;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

/**
 * Benchmark, which will execute on an Android device.
 * <p>
 * The while loop will measure the contents of the loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4.class)
public class ModelsBenchmark {

    @Rule
    public BenchmarkRule mBenchmarkRule = new BenchmarkRule();

    private final Context context = ApplicationProvider.getApplicationContext();
    private String modelsJson;

    @Before
    public void setup() throws IOException {
        try (InputStream open = context.getAssets().open("models.json")) {
            modelsJson = new BufferedReader(new InputStreamReader(open))
                    .lines().collect(Collectors.joining("\n"));
        }
    }

    @Test
    public void old() {
        Map<Long, com.example.benchmark.json_parse.old.Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        while (state.keepRunning()) {
            mModels = new HashMap<>();
            JSONObject modelarray = new JSONObject(modelsJson);
            JSONArray ids = modelarray.names();
            if (ids != null) {
                for (String id : ids.stringIterable()) {
                    Model o = new Model(modelarray.getJSONObject(id));
                    mModels.put(o.getLong("id"), o);
                }
            }
        }

        mModels.clear();
    }


    @Test
    public void gson() {
        Map<Long, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        Gson gson = new Gson();

        while (state.keepRunning()) {
            mModels = gson.fromJson(modelsJson, new TypeToken<Map<Long, N_Model>>() {
            }.getType());
        }

        mModels.clear();
    }


    @Test
    public void jackson() throws JsonProcessingException {
        Map<Long, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        while (state.keepRunning()) {
            mModels = objectMapper.readValue(modelsJson, new TypeReference<Map<Long, N_Model>>() {
            });
        }

        mModels.clear();
    }


    @Test
    public void jackson_jr() throws IOException {
        Map<String, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();


        while (state.keepRunning()) {
            mModels = JSON.std.mapOfFrom(N_Model.class, modelsJson);
        }

        mModels.clear();
    }


    @Test
    public void moshi() throws IOException {
        Map<Long, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Map<Long, N_Model>> adapter = moshi.adapter(Types.newParameterizedType(Map.class, Long.class, N_Model.class));

        while (state.keepRunning()) {
            mModels = adapter.fromJson(modelsJson);
        }

        mModels.clear();
    }

}