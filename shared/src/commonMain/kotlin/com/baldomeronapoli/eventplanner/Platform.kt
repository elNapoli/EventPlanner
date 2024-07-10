package com.baldomeronapoli.eventplanner

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform