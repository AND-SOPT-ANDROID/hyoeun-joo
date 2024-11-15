package org.sopt.and.feature.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.MyPageRepository
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {

    private val _hobby = MutableStateFlow<String?>(null)
    val hobby: StateFlow<String?> = _hobby.asStateFlow()

    fun loadHobby(token: String) {
        viewModelScope.launch {
            val result = myPageRepository.getMyHobby(token)
            result.onSuccess { response ->
                _hobby.value = response.hobby
            }.onFailure {
                _hobby.value = "데이터를 불러오는데 실패했습니다."
            }
        }
    }
}
