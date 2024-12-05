package org.sopt.and.domain.mapper

import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.feature.model.ResponseSignUpModel
import org.sopt.and.feature.model.UserInfo

fun UserInfo.toRequestSignUp(): RequestSignUpDto = RequestSignUpDto(
    userName = this.userName,
    password = this.password,
    hobby = this.hobby
)

fun ResponseSignUpDto.toResponseSignUpModel(): ResponseSignUpModel = ResponseSignUpModel(
    userNumber = this.userNumber.toString(),
)