package org.sopt.and.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("result")
    val result: T? = null,
    @SerialName("code")
    val code: String? = null,
    @SerialName("error")
    val error: BaseError? = null,
) {
    @Serializable
    data class BaseError(
        @SerialName("code")
        val code: Int,
        @SerialName("message")
        val message: String,
    )
}
