package com.baldomeronapoli.eventplanner.mapper

interface Mapper<Cto, ModelDomain> {
    fun transformFromCtoToModelDomain(): ModelDomain
}