package com.example.benchmark;

import android.content.Context;

import androidx.benchmark.BenchmarkState;
import androidx.benchmark.junit4.BenchmarkRule;
import androidx.test.core.app.ApplicationProvider;

import com.example.benchmark.json_parse.models.object_model.OModel;
import com.example.benchmark.json_parse.models.primitives_model.PModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelsBenchmark2 {


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
    public void primitives_jackson() throws IOException {
        Map<Long, PModel> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        ObjectMapper objectMapper = new ObjectMapper();

        while (state.keepRunning()) {
            state.pauseTiming();
            InputStream input = context.getAssets().open("models.json");
            state.resumeTiming();

            mModels = objectMapper.readValue(input, new TypeReference<Map<Long, PModel>>() {});
        }

        mModels.clear();
    }



    @Test
    public void object_jackson() throws IOException {
        Map<Long, OModel> mModels = null;

        final BenchmarkState state = mBenchmarkRule.getState();
        ObjectMapper objectMapper = new ObjectMapper();

        while (state.keepRunning()) {
            mModels = objectMapper.readValue(modelsJson, new TypeReference<Map<Long, OModel>>() {});
        }

        mModels.clear();
    }


}
