package com.example.benchmark;

import android.content.Context;

import androidx.benchmark.BenchmarkState;
import androidx.benchmark.junit4.BenchmarkRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.benchmark.json_parse.models.N_Deck;
import com.example.benchmark.json_parse.models.N_Model;
import com.example.benchmark.json_parse.old.Deck;
import com.example.benchmark.json_parse.old.JSONArray;
import com.example.benchmark.json_parse.old.JSONObject;
import com.example.benchmark.json_parse.old.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Benchmark, which will execute on an Android device.
 * <p>
 * The while loop will measure the contents of the loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4.class)
public class DecksBenchmark {

    @Rule
    public BenchmarkRule mBenchmarkRule = new BenchmarkRule();

    private final Context context = ApplicationProvider.getApplicationContext();
    private String decksJson;

    @Before
    public void setup() throws IOException {
        try (InputStream open = context.getAssets().open("decks.json")) {
            decksJson = new BufferedReader(new InputStreamReader(open))
                    .lines().collect(Collectors.joining("\n"));
        }
    }

    @Test
    public void old() {
        Map<Long, Deck> mDecks = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        while (state.keepRunning()) {
            JSONObject decksarray = new JSONObject(decksJson);
            JSONArray ids = decksarray.names();
            mDecks = new HashMap<>(decksarray.length());
            for (String id: ids.stringIterable()) {
                Deck o = new Deck(decksarray.getJSONObject(id));
                long longId = Long.parseLong(id);
                mDecks.put(longId, o);
            }
        }

        mDecks.clear();
    }


    @Test
    public void gson() {
        Map<Long, N_Deck> mDecks = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        Gson gson = new Gson();

        while (state.keepRunning()) {
            mDecks = gson.fromJson(decksJson, new TypeToken<Map<Long, N_Deck>>() {
            }.getType());
        }

        mDecks.clear();
    }


    @Test
    public void jackson() throws JsonProcessingException {
        Map<Long, N_Deck> mDecks = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        while (state.keepRunning()) {
            mDecks = objectMapper.readValue(decksJson, new TypeReference<Map<Long, N_Deck>>() {
            });
        }

        mDecks.clear();
    }


    @Test
    public void jackson_jr() throws IOException {
        Map<String, N_Deck> mDecks = null;

        final BenchmarkState state = mBenchmarkRule.getState();


        while (state.keepRunning()) {
            mDecks = JSON.std.mapOfFrom(N_Deck.class, decksJson);
        }

        mDecks.clear();
    }


    @Test
    public void moshi() throws IOException {
        Map<Long, N_Deck> mDecks = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Map<Long, N_Deck>> adapter = moshi.adapter(Types.newParameterizedType(Map.class, Long.class, N_Deck.class));

        while (state.keepRunning()) {
            mDecks = adapter.fromJson(decksJson);
        }

        mDecks.clear();
    }

}