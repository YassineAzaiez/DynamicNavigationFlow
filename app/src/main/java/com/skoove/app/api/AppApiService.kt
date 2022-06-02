package com.skoove.app.api

import com.skoove.shared.utils.FETCH_EXPERIMENTS
import com.skoove.shared.utils.LOGIN
import com.skoove.shared.utils.SUBMIT_SELECTION
import retrofit2.Response
import retrofit2.http.GET

interface AppApiService {

    @GET(LOGIN)
    suspend fun login(): Response<String>

    @GET(FETCH_EXPERIMENTS)
    suspend fun fetchExperiments(): Response<String>

    //TODO update Response return type
    @GET(SUBMIT_SELECTION)
    suspend fun submitSelection(): Response<Unit>
}