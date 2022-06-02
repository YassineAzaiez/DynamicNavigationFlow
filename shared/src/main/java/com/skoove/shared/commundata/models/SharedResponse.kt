package com.skoove.shared.commundata.models

data class SharedResponse<T>(
    val isSuccessful: Boolean,
    val body: SharedBody<T>
)