package org.sopt.and.domain.repositoryimpl

import org.sopt.and.data.service.MyPageService
import org.sopt.and.domain.repository.MyPageRepository
import org.sopt.and.feature.model.ResponseMyHobbyModel
import javax.inject.Inject


class MyPageRepositoryImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageRepository {

    override suspend fun getMyHobby(token: String): Result<ResponseMyHobbyModel> = runCatching {
        val response = myPageService.getMyHobby(token)

        response.result?.let {
            ResponseMyHobbyModel(hobby = it.hobby)
        } ?: throw Exception("데이터를 불러오는데 실패했습니다")
    }
}
