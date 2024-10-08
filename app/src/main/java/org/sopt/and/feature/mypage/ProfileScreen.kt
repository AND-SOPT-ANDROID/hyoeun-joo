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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun ProfileScreen(userName: String) {

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
                userName + "님",
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
        MyPagePurchase("첫 결제 시 첫 달 100원!")
        Spacer(modifier = Modifier.padding(top = 8.dp))
        MyPagePurchase("현재 보유하신 이용권이 없습니다. ")
        VideoList("전체 시청내역", "시청내역이 없어요.")
        VideoList("관심 프로그램", "관심 프로그램이 없어요.")

    }
}