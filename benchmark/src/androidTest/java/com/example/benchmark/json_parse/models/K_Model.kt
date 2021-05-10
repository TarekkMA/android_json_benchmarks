package com.example.benchmark.json_parse.models

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import kotlinx.serialization.Serializable
import java.io.IOException
import java.util.*

@Serializable
data class K_Model (
    var sortf: Long = 0,
    var did: Long = 0,
    var latexPre: String? = null,
    var latexPost: String? = null,
    var mod: Long = 0,
    var usn: Long = 0,
    var type: Long = 0,
    var css: String? = null,
)
