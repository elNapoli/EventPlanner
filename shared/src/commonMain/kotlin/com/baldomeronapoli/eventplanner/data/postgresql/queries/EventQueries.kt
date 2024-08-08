package com.baldomeronapoli.eventplanner.data.postgresql.queries

import co.touchlab.kermit.Logger
import com.baldomero.napoli.supabase.network.NetworkModule
import com.baldomeronapoli.eventplanner.data.postgresql.Scheme
import com.baldomeronapoli.eventplanner.data.postgresql.Storage.BUCKET_NAME
import com.baldomeronapoli.eventplanner.data.postgresql.Storage.generatePathFile
import com.baldomeronapoli.eventplanner.data.postgresql.dto.BaseDto
import com.baldomeronapoli.eventplanner.data.postgresql.dto.BaseRequest
import com.baldomeronapoli.eventplanner.data.postgresql.dto.BoardGameDTO
import com.baldomeronapoli.eventplanner.data.postgresql.dto.DbOperationResponse
import com.baldomeronapoli.eventplanner.data.postgresql.dto.EventDTO
import com.baldomeronapoli.eventplanner.data.postgresql.requests.AttendeeEventPagination
import com.baldomeronapoli.eventplanner.data.postgresql.requests.NearbyEventsRequest
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.rpc
import io.github.jan.supabase.storage.storage
import kotlin.time.Duration.Companion.minutes

class EventQueries(private val supabaseClient: NetworkModule) {


    @NativeCoroutines
    suspend fun getEventsByAttendee(page: Int = 1): BaseDto<List<EventDTO>> {
        val result = supabaseClient.client.postgrest.rpc(
            function = "get_user_attended_events_with_details",
            parameters = BaseRequest(
                AttendeeEventPagination(
                    recordsPerPage = 10, // TODO: si esto no se dine aca. el parameters no lo toma
                    currentPage = page
                )
            )
        )

        val events = result.decodeAs<BaseDto<List<EventDTO>>>()
        events.data.map { event ->
            val bucket = supabaseClient.client.storage.from(event.thumbnail.bucketId)
            val url = bucket.createSignedUrl(path = event.thumbnail.name, expiresIn = 1.minutes) {
                size(width = 228, height = 168)
            }
            event.thumbnail.name = url
        }
        return events
    }


    @NativeCoroutines
    suspend fun getNearbyEvents(page: Int, lat: Double, lon: Double): BaseDto<List<EventDTO>> {
        val result = supabaseClient.client.postgrest.rpc(
            function = "get_nearby_events",
            parameters = BaseRequest(
                NearbyEventsRequest(
                    currentPage = page,
                    recordsPerPage = 10,
                    latitude = lat,
                    longitude = lon
                )
            )
        )
        Logger.e(result.data)
        val events = result.decodeAs<BaseDto<List<EventDTO>>>()

        events.data.map { event ->
            val bucket = supabaseClient.client.storage.from(event.thumbnail.bucketId)
            val url = bucket.createSignedUrl(path = event.thumbnail.name, expiresIn = 1.minutes)
            event.thumbnail.name = url
        }
        return events
    }

    @NativeCoroutines
    suspend fun saveEventInBD(
        event: EventDTO,
        file: ByteArray
    ): Boolean {
        val data = supabaseClient.client.postgrest.rpc(
            function = Scheme.Event.Functions.SETUP_EVENT_WITH_DETAILS,
            parameters = BaseRequest(event)
        ).decodeAs<BaseDto<DbOperationResponse>>()
        val path = generatePathFile(data.data.id)
        supabaseClient.client.storage.from(BUCKET_NAME)
            .upload(path, file)
        return true
    }

    @NativeCoroutines
    suspend fun searchBoardGames(
        query: String
    ): List<BoardGameDTO?> {
        val b = supabaseClient.client.from(Scheme.BOARD_GAME_TABLE)
            .select(Columns.raw("*")) {
                filter {
                    ilike("name", "%${query}%")
                }
            }.data
        val a = supabaseClient.client.from(Scheme.BOARD_GAME_TABLE)
            .select(Columns.raw("*")) {
                filter {
                    ilike("name", "%${query}%")
                }
            }.decodeList<BoardGameDTO>()
        return a
    }
}
