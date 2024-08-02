package com.baldomeronapoli.eventplanner.android.mocks

import com.baldomeronapoli.eventplanner.domain.models.Event

object EventsMock {
    val event = Event(
        thumbnailUrl = "https://firebasestorage.googleapis.com/v0/b/eventplanner-6d192.appspot.com/o/LBFOdNg8yve86yJApB5WH920rN12%2F2bb34c38-d62e-4213-b0e9-3da84ee9a765%2Fthumbnail_1722042570842_2bb34c38-d62e-4213-b0e9-3da84ee9a765.jpg?alt=media&token=8f617546-1934-48d5-8d57-07a0c0b858d8",
        title = "Shawn Mendes The Virtual Tour",
        description = "Coldplay are a British rock band formed in London in 1996. The band consists of vocalist, rhythm guitarist, and pianist Chris Martin; lead guitarist Jonny Buckland; bassist Guy Berryman; drummer Will Champion; and creative director Phil Harvey. Read More",
        boardgames = emptyList(),
        slots = 5,
        //  date = Timestamp.now(),
        isPrivate = false,
        price = 0.0,
    )
}