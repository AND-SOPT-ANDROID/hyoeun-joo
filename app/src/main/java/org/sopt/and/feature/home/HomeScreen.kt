package org.sopt.and.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sopt.and.R

@Composable
fun HomeScreen() {
    val bannerItems = listOf(
        HomeBannerItem(R.drawable.ic_radiostar, "Title 1", "Description 1"),
        HomeBannerItem(R.drawable.ic_solo, "Title 2", "Description 2"),
        HomeBannerItem(R.drawable.ic_soldier, "Title 3", "Description 3"),
        HomeBannerItem(R.drawable.ic_scandal, "Title 4", "Description 4"),
        HomeBannerItem(R.drawable.ic_savehomes, "Title 4", "Description 4"),

        )
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HomeTopBar()
        Spacer(modifier = Modifier.height(16.dp))
        HomeTopBanner(bannerItem = bannerItems)
        HomeVideoList("믿고 보는 에디터 추천작", bannerItems)
        HomeVideoList("오늘의 TOP 20", bannerItems, true)
    }
}

@Composable
fun HomeTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF1B1B1B))
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio(264f / 116f)
                .align(Alignment.CenterStart)
        )
    }
}

@Composable
fun HomeVideoList(
    title: String,
    items: List<HomeBannerItem>,
    showIndex: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
        }

        Box {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(items) { index, item ->
                    Box {
                        HomeVideoListItem(item)
                        if (showIndex) {
                            Text(
                                text = (index + 1).toString(),
                                color = Color.White,
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(end = 6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeVideoListItem(item: HomeBannerItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun HomeTopBanner(
    bannerItem: List<HomeBannerItem>,
    modifier: Modifier = Modifier,
) {
    val coroutine = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 400,
        pageCount = { Int.MAX_VALUE }
    )
    LaunchedEffect(true) {
        while (true) {
            delay(3000)
            coroutine.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier.height(450.dp),
        beyondViewportPageCount = 1,

        contentPadding = PaddingValues(horizontal = 24.dp),
        pageSpacing = 12.dp
    ) { page ->
        val currentPage = page % bannerItem.size
        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = bannerItem[currentPage].imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "${currentPage + 1} / ${bannerItem.size}",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(20.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalBannerPagerPreview() {
    HomeScreen()
}
