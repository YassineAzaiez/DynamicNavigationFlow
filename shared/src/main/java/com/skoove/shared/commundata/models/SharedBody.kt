package com.skoove.shared.commundata.models

data class SharedBody<T>(
    val type: String?,
    val result: T
) {
    constructor(result: T) : this("", result)
}