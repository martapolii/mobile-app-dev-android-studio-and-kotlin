package com.example.midtermcandytemplate.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.midtermcandytemplate.R
import com.example.midtermcandytemplate.model.Candy

/**
 * Reusable list item for the home screen LazyColumn.
 */
@Composable
fun CandyListItem(
    candy: Candy,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Candy placeholder image",
                modifier = Modifier.size(56.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Text
                Text(
                    text = candy.name,
                    style = MaterialTheme.typography.titleMedium
                )

                // Text
                Text(
                    text = "${candy.type} • ${candy.flavorProfile}",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Text
                Text(
                    text = if (candy.isSugarFree) "Sugar-free option selected" else "Regular sugar option selected",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            if (candy.isFavorite) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favorite candy",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}
