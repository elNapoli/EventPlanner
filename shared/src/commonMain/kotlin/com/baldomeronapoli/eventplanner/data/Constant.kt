package com.baldomeronapoli.eventplanner.data

import com.baldomeronapoli.eventplanner.shared.MySecrets

object Constant {
    const val ALGOLIA_URL =
        "https://${MySecrets.ALGOLIA_APPLICATION_ID}-dsn.algolia.net/1/indexes/name/query"
}