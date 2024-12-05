package org.sopt.and.domain.repositoryimpl

import org.json.JSONObject
import org.sopt.and.data.service.AuthService
import org.sopt.and.domain.mapper.toRequestSignUp
import org.sopt.and.domain.mapper.toResponseSignUpModel
import org.sopt.and.domain.repository.SignUpRepository
import org.sopt.and.feature.model.ResponseSignUpModel
import org.sopt.and.feature.model.UserInfo
import retrofit2.HttpException
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : SignUpRepository {

    override suspend fun postSignUp(user: UserInfo): Result<ResponseSignUpModel> {
        return runCatching {
            val requestDto = user.toRequestSignUp()
            val response = authService.postSignUp(requestDto)

            response.result?.toResponseSignUpModel() ?: throw Exception("null")
        }.mapCatching {
            requireNotNull(it)
        }.recoverCatching { throwable ->
            val errorMessage = throwable.handleThrowable()
            ResponseSignUpModel(userNumber = null).apply {
                throw Exception(errorMessage)
            }
        }
    }

    fun Throwable.handleThrowable(): String {
        return when (this) {
            is HttpException -> {
                val statusCode = this.code()
                val errorBody = this.response()?.errorBody()?.string()
                val errorCode = JSONObject(errorBody).optString("code", "Unknown error")

                when {
                    statusCode == 400 && errorCode == "01" -> "모든 필드는 8자 이하여야 합니다"
                    statusCode == 409 && errorCode == "00" -> "이미 존재하는 이름입니다"
                    else -> "알 수 없는 오류 발생"
                }
            }
            else -> "알 수 없는 오류 발생"
        }
    }
}
