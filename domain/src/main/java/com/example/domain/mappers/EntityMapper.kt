package com.example.domain.mappers

interface EntityMapper<M , ME > {
    fun mapToDomain(data: ME): M
    fun mapToData(domain: M): ME
}