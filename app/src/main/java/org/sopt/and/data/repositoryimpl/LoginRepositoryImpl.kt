package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.service.AuthService
import org.sopt.and.domain.mapper.toRequestLogin
import org.sopt.and.domain.mapper.toResponseLoginModel
import org.sopt.and.domain.repository.LoginRepository
import org.sopt.and.domain.utils.handleThrowable
import org.sopt.and.domain.entity.LoginInfo
import org.sopt.and.domain.entity.Token
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : LoginRepository {

    override suspend fun postLogin(user: LoginInfo): Result<Token> {
        return runCatching {
            val requestDto = user.toRequestLogin()
            val response = authService.postLogin(requestDto)
            response.result?.toResponseLoginModel() ?: throw Exception("null")
        }.mapCatching {
            requireNotNull(it)
        }.recoverCatching { throwable ->
            return throwable.handleThrowable()
        }
    }
}
