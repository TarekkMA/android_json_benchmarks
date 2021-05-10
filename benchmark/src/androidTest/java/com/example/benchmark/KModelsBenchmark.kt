package com.example.benchmark

import android.content.Context
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.benchmark.json_parse.models.K_Model
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.stream.Collectors

/**
 * Benchmark, which will execute on an Android device.
 *
 *
 * The while loop will measure the contents of the loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class KModelsBenchmark {
    @get:Rule
    val benchmarkRule = BenchmarkRule()


    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var modelsJson: String

    @Before
    @Throws(IOException::class)
    fun setup() {
        context.assets.open("models.json").use { open ->
            modelsJson = BufferedReader(InputStreamReader(open))
                    .lines().collect(Collectors.joining("\n"))
        }
    }

    @Test
    fun benchmarkSomeWork() {
        var mModels: Map<Long, K_Model>? = null
        val format = Json { ignoreUnknownKeys = true }


        benchmarkRule.measureRepeated {
            mModels = format.decodeFromString<Map<Long, K_Model>>(modelsJson)
        }

        print(mModels!!.entries.first().key);
    }
}