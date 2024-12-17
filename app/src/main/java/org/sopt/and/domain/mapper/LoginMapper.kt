package org.sopt.and.domain.mapper

import org.sopt.and.data.dto.request.RequestLoginDto
import org.sopt.and.data.dto.response.ResponseLoginDto
import org.sopt.and.domain.entity.LoginInfo
import org.sopt.and.domain.entity.Token


fun LoginInfo.toRequestLogin(): RequestLoginDto = RequestLoginDto(
    userName = this.userName,
    password = this.password
)

fun ResponseLoginDto.toResponseLoginModel(): Token = Token(
    token = this.token
)
