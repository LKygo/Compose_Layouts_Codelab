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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kygoinc.composelayoutscodelab.ui.theme.ComposeLayoutsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLayoutsCodelabTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) {}
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
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
            modifier = Modifier.padding(8.dp), R.drawable.ab1_inversions, R.string.inversions
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFDDDDDD)
@Composable
fun FavouritesCardPreview() {
    ComposeLayoutsCodelabTheme {
        FavouritesCard(modifier = Modifier, R.drawable.fc1_short_mantras, R.string.short_mantras)
    }
}