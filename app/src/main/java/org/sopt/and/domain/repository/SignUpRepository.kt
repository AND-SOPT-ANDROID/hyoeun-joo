package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.UserNumber
import org.sopt.and.domain.entity.UserInfo

interface SignUpRepository {
    suspend fun postSignUp(user: UserInfo): Result<UserNumber>
}
