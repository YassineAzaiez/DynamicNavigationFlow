package com.skoove.shared.commundata

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}