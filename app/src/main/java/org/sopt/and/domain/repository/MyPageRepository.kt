package org.sopt.and.domain.repository

import org.sopt.and.feature.model.ResponseMyHobbyModel

interface MyPageRepository {
    suspend fun getMyHobby(token: String): Result<ResponseMyHobbyModel>
}