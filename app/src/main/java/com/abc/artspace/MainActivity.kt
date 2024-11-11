package com.abc.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.automirrored.sharp.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abc.artspace.ui.theme.ArtSpaceTheme
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent { ArtSpaceApp() }
    }
}

// TODO: Full image with resize
// TODO: check layouts for different screens
// TODO: use dimens?
// TODO: Check how it's done in gallery apps
// TODO: Use ViewModel to save states outside of the Activity
// TODO: Make various frames for each image (curly, wavy, angular, etc.)
// TODO: Use Room for storing paintings

@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun ArtSpaceApp() {
    ArtSpaceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArtSpaceLayout(
                Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

// Modifier.fillMaxSize().imePadding()
// safeDrawingPadding() - without system bars
// safeContentPadding = safeDrawingPadding() + safeGesturePadding() - without system bars and gestures areas

data class Painting(
    @DrawableRes val image: Int,
    @StringRes val contentDescription: Int,
    @StringRes val name: Int,
    @StringRes val author: Int,
    @StringRes val yearPublished: Int
)

// TODO: Maybe use constraint layout or check out custom layouts with placeholders. or use Scaffold for this one
@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

    val gallery = listOf(
        Painting(
            image = R.drawable.aik_a_cloud_over_the_steppe,
            contentDescription = R.string.aik_a_cloud_over_the_steppe,
            name = R.string.aik_a_cloud_over_the_steppe,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_a_cloud_over_the_steppe_year
        ), Painting(
            image = R.drawable.aik_a_mountain_slope_crimea,
            contentDescription = R.string.aik_a_mountain_slope_crimea,
            name = R.string.aik_a_mountain_slope_crimea,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_a_mountain_slope_crimea_year
        ), Painting(
            image = R.drawable.aik_birch_grove,
            contentDescription = R.string.aik_birch_grove,
            name = R.string.aik_birch_grove,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_birch_grove_year
        ), Painting(
            image = R.drawable.aik_dnipro_in_the_morning,
            contentDescription = R.string.aik_dnipro_in_the_morning,
            name = R.string.aik_dnipro_in_the_morning,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_dnipro_in_the_morning_year
        ), Painting(
            image = R.drawable.aik_elbrus_a_moonlit_night,
            contentDescription = R.string.aik_elbrus_a_moonlit_night,
            name = R.string.aik_elbrus_a_moonlit_night,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_elbrus_a_moonlit_night_year
        ), Painting(
            image = R.drawable.aik_patches_of_moonlight_in_the_forest_winter,
            contentDescription = R.string.aik_patches_of_moonlight_in_the_forest_winter,
            name = R.string.aik_patches_of_moonlight_in_the_forest_winter,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_patches_of_moonlight_in_the_forest_winter_year
        ), Painting(
            image = R.drawable.aik_rainbow,
            contentDescription = R.string.aik_rainbow,
            name = R.string.aik_rainbow,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_rainbow_year
        ), Painting(
            image = R.drawable.aik_sea_crimea,
            contentDescription = R.string.aik_sea_crimea,
            name = R.string.aik_sea_crimea,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_sea_crimea_year
        ), Painting(
            image = R.drawable.aik_the_clouds,
            contentDescription = R.string.aik_the_clouds,
            name = R.string.aik_the_clouds,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_the_clouds_year
        ), Painting(
            image = R.drawable.aik_wave,
            contentDescription = R.string.aik_wave,
            name = R.string.aik_wave,
            author = R.string.arkhip_ivanovich_kuindzhi,
            yearPublished = R.string.aik_wave_year
        )
    )

    var showingPosition by remember { mutableIntStateOf(0) }
    var showingPainting by remember { mutableStateOf(gallery[showingPosition]) }

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Art(
            painting = showingPainting,
            modifier = Modifier
                .padding(top = 32.dp)
                .sizeIn(maxWidth = 750.dp, maxHeight = 550.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        ArtInfo(
            painting = showingPainting,
            modifier = Modifier
                .widthIn(max = 400.dp)
                .padding(bottom = 32.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        )

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FunctionalButton(
                icon = Icons.AutoMirrored.Sharp.ArrowBack,
                onClick = { showingPainting = gallery[abs(--showingPosition % gallery.size)] },
                modifier = Modifier
                    .size(56.dp)
                    .shadow(elevation = 4.dp, shape = CircleShape)
            )
            FunctionalButton(
                icon = Icons.AutoMirrored.Sharp.ArrowForward,
                onClick = { showingPainting = gallery[abs(++showingPosition % gallery.size)] },
                modifier = Modifier
                    .size(56.dp)
                    .shadow(elevation = 4.dp, shape = CircleShape)
            )
        }
    }
}

@Composable
fun Art(modifier: Modifier = Modifier, painting: Painting) {
    Card(
        modifier = modifier,
        shape = RectangleShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Image(
            painter = painterResource(painting.image),
            contentDescription = stringResource(painting.contentDescription),
            modifier = Modifier.padding(8.dp),
            contentScale = ContentScale.Inside
        )
    }

}

@Composable
fun ArtInfo(modifier: Modifier = Modifier, painting: Painting) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(painting.name),
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.displaySmall,
            fontSize = 40.sp,
            lineHeight = 25.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${stringResource(painting.author)} (${stringResource(painting.yearPublished)})",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FunctionalButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    FilledTonalIconButton(
        onClick = onClick, modifier = modifier,
        shape = CircleShape
    ) {
        Icon(icon, contentDescription)
    }
}


//val pagerState = rememberPagerState(pageCount = { gallery.size })
//
//val fling = PagerDefaults.flingBehavior(
//    state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(4)
//)
//
//val coroutineScope = rememberCoroutineScope()
//
//Column(
//modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
//) {
//    HorizontalPager(
//        modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .weight(1f),
//        state = pagerState,
//        flingBehavior = fling,
//        pageSpacing = 16.dp,
//        contentPadding = PaddingValues(horizontal = 16.dp)
//    ) { painting ->
//        Column(modifier = Modifier.graphicsLayer {
//            val pageOffset =
//                ((pagerState.currentPage - painting) + pagerState.currentPageOffsetFraction).absoluteValue
//            alpha = lerp(
//                start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
//            )
//        }, horizontalAlignment = Alignment.CenterHorizontally) {
//            Art(
//                painting = gallery[painting],
//                modifier = Modifier
//                    .padding(top = 32.dp)
//                    .sizeIn(maxWidth = 750.dp, maxHeight = 550.dp)
//
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//            ArtInfo(
//                painting = gallery[painting],
//                modifier = Modifier
//                    .widthIn(max = 400.dp)
//                    .padding(bottom = 32.dp)
//                    .background(MaterialTheme.colorScheme.secondaryContainer)
//                    .padding(vertical = 8.dp, horizontal = 16.dp)
//            )
//        }
//    }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(0.9f)
//            .padding(bottom = 16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        FunctionalButton(
//            icon = Icons.AutoMirrored.Sharp.ArrowBack, onClick = {
//                coroutineScope.launch {
//                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
//                }
//            }, enabled = pagerState.canScrollBackward, modifier = Modifier.size(56.dp)
//        )
//        FunctionalButton(
//            icon = Icons.AutoMirrored.Sharp.ArrowForward, onClick = {
//                coroutineScope.launch {
//                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                }
//            }, enabled = pagerState.canScrollForward, modifier = Modifier.size(56.dp)
//        )
//    }
//}