package com.cestrada.hilttest.service

import com.cestrada.hilttest.model.Blog
import com.cestrada.hilttest.utils.EntityMappers
import javax.inject.Inject

class NetworkMapper @Inject constructor(): EntityMappers<BlogNetworkEntity, Blog>{

    override fun fromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun fromDomainModel(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog>{
        return entities.map{fromEntity(it)}
    }

}