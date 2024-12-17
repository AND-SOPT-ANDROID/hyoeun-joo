package org.sopt.and.feature.mypage.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.core.component.BaseViewModel
import org.sopt.and.domain.repository.MyPageRepository
import org.sopt.and.feature.mypage.model.MyPageContract.MyPageEvent
import org.sopt.and.feature.mypage.model.MyPageContract.MyPageSideEffect
import org.sopt.and.feature.mypage.model.MyPageContract.MyPageState
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : BaseViewModel<MyPageState, MyPageSideEffect, MyPageEvent>() {

    override fun createInitialState() = MyPageState()

    override suspend fun handleEvent(event: MyPageEvent) {
        when (event) {
            is MyPageEvent.LoadHobby -> loadHobby(event.token)
        }
    }

    private fun loadHobby(token: String) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            myPageRepository.getMyHobby(token)
                .onSuccess { response ->
                    setState { copy(hobby = response.hobby, isLoading = false) }
                }
                .onFailure {
                    setState { copy(hobby = "", isLoading = false, error = "데이터를 불러오는데 실패했습니다.") }
                    setSideEffect { MyPageSideEffect.ShowErrorToast("데이터를 불러오는데 실패했습니다.") }
                }
        }
    }
}
