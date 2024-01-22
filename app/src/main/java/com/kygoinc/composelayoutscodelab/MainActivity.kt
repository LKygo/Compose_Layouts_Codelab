package com.kygoinc.composelayoutscodelab

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kygoinc.composelayoutscodelab.ui.theme.ComposeLayoutsCodelabTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ComposeLayoutsCodelabTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {

    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { input = it},
        placeholder = { Text(text = stringResource(R.string.search)) },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            errorTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
    )
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier, @DrawableRes imageId: Int, @StringRes textId: Int
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(imageId),
            contentDescription = "Inversions",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(textId),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)

        )

    }

}

@Composable
fun FavouritesCard(
    modifier: Modifier = Modifier, @DrawableRes imageId: Int, @StringRes textId: Int
) {

    Surface(
        modifier = modifier.padding(8.dp),
        shape = MaterialTheme.shapes.small,
    ) {

        Row(
            modifier = modifier.width(192.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {

            Image(
                painterResource(id = imageId),
                contentDescription = "short_mantras",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = modifier.size(56.dp)

            )
            Text(
                text = stringResource(textId),
                style = MaterialTheme.typography.labelLarge,
                modifier = modifier.padding(horizontal = 16.dp)

            )
        }
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(imageId = item.drawable, textId = item.text)
        }
    }
}

@Composable
fun FavoriteCollectionsRow(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = modifier.height(145.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
    )
    {
        items(favoriteCollectionsData) { item ->
            FavouritesCard(imageId = item.drawable, textId = item.text)
        }
    }
}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            stringResource(id = titleId).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight(weight = 400),
            modifier = modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFDDDDDD))
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier =Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(titleId = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(titleId = R.string.favorite_collections) {
            FavoriteCollectionsRow()
        }

        Spacer(modifier =Modifier.height(56.dp))
    }
}

@Composable
fun MainBottomNavigation(
    modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = 8.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
            label = { Text("Favorite") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = { MainBottomNavigation(Modifier.height(56.dp)) }

    ) { padding ->
        HomeScreen(Modifier.padding(16.dp))
    }
}


private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)


@Preview(showBackground = true, widthDp = 320)
@Composable
fun SearchFieldPreview() {
    ComposeLayoutsCodelabTheme {
        SearchBar(modifier = Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    ComposeLayoutsCodelabTheme {
        AlignYourBodyElement(
            modifier = Modifier.padding(8.dp), R.drawable.ab1_inversions, R.string.ab1_inversions
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFDDDDDD)
@Composable
fun FavouritesCardPreview() {
    ComposeLayoutsCodelabTheme {
        FavouritesCard(
            modifier = Modifier, R.drawable.fc1_short_mantras, R.string.fc1_short_mantras
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFDDDDDD)
@Composable
fun AlignYourBodyRowPreview() {
    ComposeLayoutsCodelabTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFDDDDDD)
@Composable
fun FavoriteCollectionsRowPreview() {
    ComposeLayoutsCodelabTheme {
        FavoriteCollectionsRow()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFDDDDDD)
@Composable
fun HomeSectionPreview() {
    ComposeLayoutsCodelabTheme {
        HomeSection(titleId = R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    ComposeLayoutsCodelabTheme {
        HomeScreen(Modifier.padding(0.dp))
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MainBottomNavigationPreview() {
    ComposeLayoutsCodelabTheme {
        MainBottomNavigation()
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun MainScreenPreview() {
    ComposeLayoutsCodelabTheme {
        MainScreen()
    }
}