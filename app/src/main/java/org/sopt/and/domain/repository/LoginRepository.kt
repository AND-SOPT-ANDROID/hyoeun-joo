package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.LoginInfo
import org.sopt.and.domain.entity.Token

interface LoginRepository {
    suspend fun postLogin(user: LoginInfo): Result<Token>
}
