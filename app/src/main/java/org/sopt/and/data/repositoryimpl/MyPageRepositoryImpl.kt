package org.sopt.and.data.repositoryimpl

import org.sopt.and.data.service.MyPageService
import org.sopt.and.domain.repository.MyPageRepository
import org.sopt.and.domain.entity.Hobby
import javax.inject.Inject


class MyPageRepositoryImpl @Inject constructor(
    private val myPageService: MyPageService
) : MyPageRepository {

    override suspend fun getMyHobby(token: String): Result<Hobby> = runCatching {
        val response = myPageService.getMyHobby(token)

        response.result?.let {
            Hobby(hobby = it.hobby)
        } ?: throw Exception("데이터를 불러오는데 실패했습니다")
    }
}
