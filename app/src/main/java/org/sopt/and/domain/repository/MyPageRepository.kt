package org.sopt.and.domain.repository

import org.sopt.and.domain.entity.Hobby

interface MyPageRepository {
    suspend fun getMyHobby(token: String): Result<Hobby>
}
