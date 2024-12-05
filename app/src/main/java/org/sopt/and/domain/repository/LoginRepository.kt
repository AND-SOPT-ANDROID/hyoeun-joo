package org.sopt.and.domain.repository

import org.sopt.and.feature.model.LoginInfo
import org.sopt.and.feature.model.ResponseLoginModel

interface LoginRepository {
    suspend fun postLogin(user: LoginInfo): Result<ResponseLoginModel>
}