package org.sopt.and.domain.mapper

import org.sopt.and.data.dto.request.RequestSignUpDto
import org.sopt.and.data.dto.response.ResponseSignUpDto
import org.sopt.and.domain.entity.UserNumber
import org.sopt.and.domain.entity.UserInfo

fun UserInfo.toRequestSignUp(): RequestSignUpDto = RequestSignUpDto(
    userName = this.userName,
    password = this.password,
    hobby = this.hobby
)

fun ResponseSignUpDto.toResponseSignUpModel(): UserNumber = UserNumber(
    userNumber = this.userNumber.toString(),
)
