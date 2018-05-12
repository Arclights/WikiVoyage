package com.tommykvant.wikivoyage.client

import com.squareup.moshi.Json
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Request


class WikiVoyageClient {

    companion object {
        private val client = OkHttpClient()
        private val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        private val detailsAdapter = moshi.adapter(WikiVoyageDetails::class.java)

        fun getDetails(url: String): WikiVoyageDetails? {
            val request = Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .build()

            System.out.println("Getting details $url")

            val response = client.newCall(request).execute()

            return response.body()?.use {
                detailsAdapter.fromJson(it.source())
            }
        }

        @Deprecated("Use one of the other functions instead")
        fun fetch(url: String): String? {
            val request = Request.Builder()
                    .url(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .build();

            System.out.println("Fetching $url")

            val response = client.newCall(request).execute()

            return response.body()?.use {
                it.string()
            }
        }
    }
}


data class WikiVoyageDetails(
        val parse: Parse
)

data class Parse(
        val title: String,
        val pageid: Long,
        val wikitext: WikiText
)

data class WikiText(
        @Json(name = "*")
        val content: String
)
