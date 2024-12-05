package org.sopt.and.data.service

import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.ResponseLoginDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/user")
    suspend fun postSignUp(
        @Body request: RequestSignUpDto
    ): BaseResponse<ResponseSignUpDto>

    @POST("/login")
    suspend fun postLogin(
        @Body request: RequestLoginDto
    ): BaseResponse<ResponseLoginDto>
}