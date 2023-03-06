package com.goodapps.invoices.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

const val DEV_HOST = "https://storage.googleapis.com/xmm-homework/"

internal fun createHttpClient(): HttpClient {
    return HttpClient() {
        expectSuccess = true
        defaultRequest {
            url(DEV_HOST)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}
