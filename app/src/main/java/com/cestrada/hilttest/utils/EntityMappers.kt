package com.cestrada.hilttest.utils

interface EntityMappers<Entity, DomainModel> {

    fun fromEntity(entity: Entity): DomainModel

    fun fromDomainModel(domainModel: DomainModel): Entity
}