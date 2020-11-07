package com.cestrada.hilttest.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.cestrada.hilttest.data.BlogDao
import com.cestrada.hilttest.data.BlogDatabase
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
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase): BlogDao {
        return blogDatabase.blogDao()
    }

}