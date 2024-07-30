package com.baldomeronapoli.eventplanner.data.collections

object EventsCollection {
    const val COLLECTION_NAME = "events"

    object EventFields {
        const val ATTENDEES_ID = "attendeesId"
    }

    object EventSubCollection {
        const val ATTENDEES = AttendeesCollection.COLLECTION_NAME
        const val BOARDGAMES = BoardGamesCollection.COLLECTION_NAME
        const val ADDRESSES = AddressesCollection.COLLECTION_NAME
    }
}