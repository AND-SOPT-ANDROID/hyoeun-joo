package org.sopt.and.feature.mypage.model

import org.sopt.and.core.component.UiEvent
import org.sopt.and.core.component.UiSideEffect
import org.sopt.and.core.component.UiState

class MyPageContract {
    data class MyPageState(
        val hobby: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : UiState

    sealed class MyPageEvent : UiEvent {
        data class LoadHobby(val token: String) : MyPageEvent()
    }

    sealed class MyPageSideEffect : UiSideEffect {
        data class ShowErrorToast(val message: String) : MyPageSideEffect()
    }
}
