package com.baldomeronapoli.eventplanner.data.postgresql.queries

import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventDTO
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class EventQueries(private val supabaseClient: SupabaseClient) {

    @NativeCoroutines
    suspend fun getEventsByAttendee(
        attendeeId: Int
    ): List<EventDTO?> {
        return supabaseClient.from("events")
            .select(Columns.raw("*, events_attendees!inner(id, users(id, email)), users(id, email), addresses(*)")) {
                filter {
                    eq("id", attendeeId)
                }
            }.decodeList<EventDTO>()
    }
}
