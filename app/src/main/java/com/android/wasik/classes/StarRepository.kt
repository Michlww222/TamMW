package com.android.wasik.classes

import com.android.wasik.data.StarResponse
import com.android.wasik.service.StarService
import retrofit2.Response

class StarRepository {

    suspend fun getStarResponse(): Response<StarResponse> =
        StarService.starService.getStarResponse()

}