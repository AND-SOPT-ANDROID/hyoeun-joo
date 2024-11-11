package org.sopt.and.data.service

import org.sopt.and.data.dto.BaseResponse
import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.ResponseLoginDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/user")
    fun postSignUp(
        @Body request: RequestSignUpDto
    ): Call<BaseResponse<ResponseSignUpDto>>

    @POST("/login")
    fun postLogin(
        @Body request: RequestLoginDto
    ): Call<BaseResponse<ResponseLoginDto>>
}