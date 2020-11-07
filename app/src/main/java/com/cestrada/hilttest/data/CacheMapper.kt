package com.cestrada.hilttest.data

import com.cestrada.hilttest.model.Blog
import com.cestrada.hilttest.service.BlogNetworkEntity
import com.cestrada.hilttest.utils.EntityMappers
import org.w3c.dom.Entity
import javax.inject.Inject

class CacheMapper @Inject constructor(): EntityMappers<BlogCacheEntity, Blog> {

    override fun fromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun fromDomainModel(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog>{
        return entities.map{fromEntity(it)}
    }
}