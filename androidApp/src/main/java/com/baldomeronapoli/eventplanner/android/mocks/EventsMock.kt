package com.baldomeronapoli.eventplanner.android.mocks

import com.baldomero.napoli.eventplannerevents.presentation.models.EventUI


object EventsMock {
    val event = EventUI(
        title = "Shawn Mendes The Virtual Tour",
        description = "Coldplay are a British rock band formed in London in 1996. The band consists of vocalist, rhythm guitarist, and pianist Chris Martin; lead guitarist Jonny Buckland; bassist Guy Berryman; drummer Will Champion; and creative director Phil Harvey. Read More",
        boardgames = emptyList(),
        slots = 5,
        //  date = Timestamp.now(),
        isPrivate = false,
        price = 0.0,
    )
}