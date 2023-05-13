package com.sooq.open.helper


abstract class MapperHelper<S, D> {

    abstract fun map(source: S): D

    fun mapList(source: List<S>): List<D> {
        return source.map(this::map)
    }

}