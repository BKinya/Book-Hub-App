package com.beatrice.bookhubapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beatrice.bookhubapp.R
import com.beatrice.bookhubapp.util.SearchInputState

const val INPUT_TAG = "searchInputTag"
const val BUTTON_TAG = "searchButtonTag"
const val CLEAR_BUTTON_TAG = "clearButtonTag"

@Composable
fun SearchInputComposable(
    modifier: Modifier = Modifier,
    inputSate: SearchInputState = rememberSearchInputState(initialText = ""),
    onValueChanged: (String) -> Unit = { newString -> inputSate.searchTerm = newString },
    onClearInput: () -> Unit = { inputSate.searchTerm = "" },
    onButtonClicked: () -> Unit = { }
) {
    Row(
        modifier = Modifier.padding(
            top = 8.dp,
            bottom = 16.dp
        )
    ) {
        val clearButton = @Composable {
            IconButton(
                onClick = onClearInput,
                modifier = modifier.testTag(CLEAR_BUTTON_TAG)
            ) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "Clear button",
                    tint = Color.Gray
                )
            }
        }

        OutlinedTextField(
            value = inputSate.searchTerm,
            onValueChange = onValueChanged,
            modifier = modifier
                .padding(end = 8.dp)
                .testTag(INPUT_TAG),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = if (inputSate.isValidInput) clearButton else null
        )

        Button(
            onClick = onButtonClicked,
            modifier = modifier
                .size(width = 64.dp, height = 50.dp)
                .testTag(BUTTON_TAG),
            enabled = inputSate.isValidInput,
            shape = RoundedCornerShape(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_outline_search_24),
                contentDescription = stringResource(R.string.search_icon)
            )
        }
    }
}

@Composable
fun rememberSearchInputState(initialText: String): SearchInputState =
    rememberSaveable(initialText, saver = SearchInputState.Saver) {
        SearchInputState(initialText)
    }