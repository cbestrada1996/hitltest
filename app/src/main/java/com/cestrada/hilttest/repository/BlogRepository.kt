package com.cestrada.hilttest.repository

import com.cestrada.hilttest.data.BlogDao
import com.cestrada.hilttest.data.CacheMapper
import com.cestrada.hilttest.model.Blog
import com.cestrada.hilttest.service.BlogServiceApi
import com.cestrada.hilttest.service.NetworkMapper
import com.cestrada.hilttest.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class BlogRepository constructor(
    private val blogDao: BlogDao,
    private val blogServiceApi: BlogServiceApi,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper) {

    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogServiceApi.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.fromDomainModel(blog))
            }

            val cacheBlog = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cacheBlog)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}