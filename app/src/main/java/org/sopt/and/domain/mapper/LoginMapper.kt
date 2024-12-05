package org.sopt.and.domain.mapper

import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.data.dto.response.ResponseLoginDto
import org.sopt.and.feature.model.LoginInfo
import org.sopt.and.feature.model.ResponseLoginModel


fun LoginInfo.toRequestLogin(): RequestLoginDto = RequestLoginDto(
    userName = this.userName,
    password = this.password
)

fun ResponseLoginDto.toResponseLoginModel(): ResponseLoginModel = ResponseLoginModel(
    token = this.token
)