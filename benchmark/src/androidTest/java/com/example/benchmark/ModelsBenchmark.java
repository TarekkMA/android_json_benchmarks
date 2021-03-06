package com.example.benchmark;

import android.content.Context;
import android.util.Log;

import androidx.benchmark.junit4.BenchmarkRule;
import androidx.benchmark.BenchmarkState;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import com.example.benchmark.json_parse.models.K_Model;
import com.example.benchmark.json_parse.models.N_Deck;
import com.example.benchmark.json_parse.models.N_Model;
import com.example.benchmark.json_parse.old.JSONArray;
import com.example.benchmark.json_parse.old.JSONObject;
import com.example.benchmark.json_parse.old.Model;
import com.example.benchmark.json_parse.treemodel.AnkiSerialization;
import com.example.benchmark.json_parse.treemodel.TMJSONArray;
import com.example.benchmark.json_parse.treemodel.TMJSONObject;
import com.example.benchmark.json_parse.treemodel.TMModel;
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

import kotlinx.serialization.json.Json;

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
    public void treemodel() {
        Map<Long, TMModel> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        while (state.keepRunning()) {
            mModels = new HashMap<>();
            TMJSONObject modelarray = new TMJSONObject(modelsJson);
            TMJSONArray ids = modelarray.names();
            if (ids != null) {
                for (String id : ids.stringIterable()) {
                    TMModel o = new TMModel(modelarray.getJSONObject(id));
                    mModels.put(o.getLong("id"), o);
                }
            }
        }

        mModels.clear();
    }

    @Test
    public void treemodel_parse_all() throws JsonProcessingException {
        Map<Long, TMModel> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        ObjectMapper objectMapper = AnkiSerialization.getObjectMapper();
        while (state.keepRunning()) {
            mModels = objectMapper.readValue(modelsJson, new TypeReference<Map<Long, TMModel>>() {});
        }

        mModels.clear();
    }



    @Test
    @Ignore
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
    @Ignore
    public void dsljson() throws IOException {
        Map<Long, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        DslJson<Object> objectDslJson = new DslJson<>(Settings.basicSetup().allowArrayFormat(true));

        while (state.keepRunning()) {
            state.pauseTiming();
            InputStream in = new ByteArrayInputStream(modelsJson.getBytes());
            state.resumeTiming();
            ParameterizedType parameterizedType = Types.newParameterizedType(Map.class, Long.class, N_Model.class);// from moshi
            mModels = (Map<Long, N_Model>) objectDslJson.deserialize(parameterizedType, in);
        }

        mModels.entrySet().stream().limit(10).forEach(e -> Log.e("TEST", e.getKey() + ":" + e.getValue().getDid()));

        mModels.clear();
    }


//
//    @Test
//    public void kotlinx() {
//        Map<Long, K_Model> mModels = null;
//
//        final BenchmarkState state = mBenchmarkRule.getState();
//        Gson gson = new Gson();
//
//        while (state.keepRunning()) {
//            Json.Default.de
//        }
//
//        mModels.clear();
//    }


    @Test
    @Ignore
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
    @Ignore
    public void jackson_jr() throws IOException {
        Map<String, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();


        while (state.keepRunning()) {
            mModels = JSON.std.mapOfFrom(N_Model.class, modelsJson);
        }

        mModels.clear();
    }

    @Test
    @Ignore
    public void jackson_streaming() throws IOException {
        Map<Long, N_Model> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();

        while (state.keepRunning()) {
            state.pauseTiming();
            final JsonParser jp = new JsonFactory().createParser(modelsJson);
            state.resumeTiming();

            mModels = new HashMap<>();
            if (jp.nextToken() != JsonToken.START_OBJECT) {
                throw new IllegalStateException("Expected '{'");
            }
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                final Long key = jp.getValueAsLong();

                jp.nextToken();
                final N_Model value = N_Model.createFromJacksonParser(jp);
                mModels.put(key, value);
            }
            state.pauseTiming();
            jp.close();
            state.resumeTiming();
        }
        mModels.clear();
    }

    @Test
    @Ignore
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