package com.app.opendeeplink.ui.comsable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.opendeeplink.R

/**
 * Display the list of deep links.
 */
@Composable
fun DeeplinkHistory(
    modifier: Modifier = Modifier,
    deeplinkList: List<String>,
    copyLink: (deeplink: String) -> Unit,
    deleteLink: (deeplink: String) -> Unit,
    openLink: (deeplink: String) -> Unit,
) {

    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = stringResource(id = R.string.history)
        )

        LazyColumn {
            items(deeplinkList) { deeplink ->
                DeeplinkItem(
                    deeplink = deeplink,
                    copyLink = copyLink,
                    deleteLink = deleteLink,
                    openLink = openLink,
                )
            }
        }
    }
}


@Composable
fun DeeplinkItem(
    deeplink: String,
    copyLink: (deeplink: String) -> Unit,
    deleteLink: (deeplink: String) -> Unit,
    openLink: (deeplink: String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                modifier = Modifier.weight(1f),
                text = deeplink,
            )

            IconButton(onClick = { copyLink(deeplink) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.copy),
                )
            }

            IconButton(onClick = { deleteLink(deeplink) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete)
                )
            }

            IconButton(onClick = { openLink(deeplink) }) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = stringResource(R.string.open_deeplink)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeeplinkHistoryPreview() {
    DeeplinkHistory(
        deeplinkList = listOf(
            "https://google.com",
            "play://media/urn",
            "tel://"
        ),
        copyLink = {},
        deleteLink = {},
        openLink = {}
    )
}


@Preview(showBackground = true)
@Composable
fun DeeplinkItemPreview() {
    DeeplinkItem(
        deeplink = "https://google.com",
        copyLink = {},
        deleteLink = {},
        openLink = {}
    )
}
