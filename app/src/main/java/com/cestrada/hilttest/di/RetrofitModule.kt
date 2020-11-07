package com.cestrada.hilttest.di

import android.app.Application
import com.cestrada.hilttest.model.Blog
import com.cestrada.hilttest.service.BlogNetworkEntity
import com.cestrada.hilttest.service.BlogServiceApi
import com.cestrada.hilttest.service.NetworkMapper
import com.cestrada.hilttest.utils.EntityMappers
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): BlogServiceApi {
        return retrofit.build().create(BlogServiceApi::class.java)
    }

}