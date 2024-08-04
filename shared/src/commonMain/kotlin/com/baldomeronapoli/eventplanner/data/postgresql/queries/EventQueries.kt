package com.baldomeronapoli.eventplanner.data.postgresql.queries

import co.touchlab.kermit.Logger
import com.baldomeronapoli.eventplanner.data.postgresql.Scheme
import com.baldomeronapoli.eventplanner.data.postgresql.dto.BoardGameDTO
import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventDTO
import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventWrapper
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.rpc

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

    @NativeCoroutines
    suspend fun saveEventInBD(
        event: EventDTO
    ): Boolean {
        supabaseClient.postgrest.rpc("setup_event_with_details", EventWrapper(event))
        return true
    }

    @NativeCoroutines
    suspend fun searchBoardGames(
        query: String
    ): List<BoardGameDTO?> {
        val b = supabaseClient.from(Scheme.BOARD_GAME_TABLE)
            .select(Columns.raw("*")) {
                filter {
                    ilike("name", "%${query}%")
                }
            }.data
        Logger.e(b.toString())
        val a = supabaseClient.from(Scheme.BOARD_GAME_TABLE)
            .select(Columns.raw("*")) {
                filter {
                    ilike("name", "%${query}%")
                }
            }.decodeList<BoardGameDTO>()
        Logger.e(a.toString())
        return a
    }
}
