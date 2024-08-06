package com.baldomeronapoli.eventplanner.data.postgresql

object Scheme {
    const val BOARD_GAME_TABLE = "board_games"

    object Event {
        const val TABLE_NAME = "events"
        const val THUMBNAIL_PATH_COLUMN_NAME = "thumbnail_path"

        object Functions {
            const val SETUP_EVENT_WITH_DETAILS = "setup_event_with_details"
        }
    }
}