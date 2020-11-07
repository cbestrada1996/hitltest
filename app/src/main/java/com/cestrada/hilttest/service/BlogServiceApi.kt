package com.cestrada.hilttest.service

import retrofit2.http.GET

interface BlogServiceApi {
    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}