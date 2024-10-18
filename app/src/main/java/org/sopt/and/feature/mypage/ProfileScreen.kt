package org.sopt.and.feature.mypage

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun ProfileScreen(viewModel: MyPageViewModel) {
    val userName by viewModel.email.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
                "$userName" + stringResource(R.string.person),
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                fontSize = 20.sp
            )
            Image(
                painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = "alarm",
                modifier = Modifier.size(80.dp),
                alignment = Alignment.CenterEnd
            )
        }
        MyPagePurchase(stringResource(R.string.profile_first_purchase_description))
        Spacer(modifier = Modifier.padding(top = 4.dp))
        MyPagePurchase(stringResource(R.string.profile_no_ticket))
        VideoList(
            stringResource(R.string.profile_total_view_history),
            stringResource(R.string.profile_no_view_history)
        )
        VideoList(
            stringResource(R.string.profile_interest_program),
            stringResource(R.string.profile_no_interest_program)
        )
    }
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
