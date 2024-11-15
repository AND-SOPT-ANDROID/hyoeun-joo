package org.sopt.and.domain.repository

import org.sopt.and.feature.model.ResponseSignUpModel
import org.sopt.and.feature.model.UserInfo

interface SignUpRepository {
    suspend fun postSignUp(user: UserInfo): Result<ResponseSignUpModel>
}