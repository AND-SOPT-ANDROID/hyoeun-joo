package org.sopt.and.feature.mypage

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.sopt.and.R
import org.sopt.and.feature.mypage.model.MyPageContract
import org.sopt.and.feature.mypage.viewmodel.MyPageViewModel

@Composable
fun ProfileScreen(viewModel: MyPageViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val token = getAuthToken(context)
        if (token != null) {
            viewModel.setEvent(MyPageContract.MyPageEvent.LoadHobby(token))
        } else {
            Log.d("ProfileScreen", "토큰 못 찾음")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is MyPageContract.MyPageSideEffect.ShowErrorToast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF252525)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_24),
                contentDescription = "Profile Image",
                Modifier.size(80.dp),
                alignment = Alignment.CenterStart
            )
            Text(
                text = when {
                    uiState.isLoading -> "로딩 중..."
                    uiState.hobby.isNotEmpty() -> uiState.hobby
                    else -> "찾을 수 없습니다"
                },
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = "Alarm Icon",
                modifier = Modifier.size(80.dp),
                alignment = Alignment.CenterEnd
            )
        }

        MyPagePurchase(stringResource(R.string.profile_first_purchase_description))
        Spacer(modifier = Modifier.padding(top = 4.dp))
        MyPagePurchase(stringResource(R.string.profile_no_ticket))
        VideoList(
            videoDescription = stringResource(R.string.profile_total_view_history),
            emptyDescription = stringResource(R.string.profile_no_view_history)
        )
        VideoList(
            videoDescription = stringResource(R.string.profile_interest_program),
            emptyDescription = stringResource(R.string.profile_no_interest_program)
        )
    }
}

fun getAuthToken(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    return sharedPreferences.getString("auth_token", null)
}

@Composable
fun MyPagePurchase(description: String) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF252525))
            .padding(top = 16.dp)
            .padding(horizontal = 10.dp)
    ) {

        Text(description, color = Color(0xFFA5A5A5))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("구매하기", color = Color.White, fontSize = 20.sp)
            Image(
                painter = painterResource(R.drawable.ic_back_right_white_24),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun VideoList(videoDescription: String, emptyDescription: String? = null) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF1B1B1B))
            .padding(horizontal = 10.dp)
            .padding(bottom = 20.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            videoDescription,
            style = TextStyle(fontWeight = FontWeight.Bold),
            color = Color.White,
            fontSize = 16.sp
        )
        EmptyVideoList(emptyDescription)
    }
}

//나중에 EmptyView로 갈아끼우기 위해 함수분리
@Composable
fun EmptyVideoList(emptyDescription: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_error_gray_24),
            contentDescription = "Empty Video List",
            modifier = Modifier
                .fillMaxWidth()
                .size(60.dp),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        emptyDescription?.let {
            Text(
                it, color = Color(0xFFA6A6A6),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),

                )
        }
    }
}
