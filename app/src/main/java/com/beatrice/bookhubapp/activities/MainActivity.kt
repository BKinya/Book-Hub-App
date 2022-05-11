package com.beatrice.bookhubapp.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beatrice.bookhubapp.R
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.ui.res.painterResource as painterResource1

class MainActivity : AppCompatActivity() {
  private val bookViewModel: BookViewModel by viewModel()
  private val searchTerm = ""
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface(color = MaterialTheme.colors.background) {
          SearchScreen(searchTerm)
        }
      }
    }

  }
}

@Composable
private fun SearchScreen(searchTerm: String) {
  Column(modifier = Modifier.padding(16.dp)) {
    Text(
      text = stringResource(R.string.enter_search_term_label),
      modifier = Modifier.padding(top = 8.dp, bottom = 12.dp),
      fontSize = 20.sp,
      textAlign = TextAlign.Start,
      fontFamily = FontFamily.Serif
    )
    SearchView(searchTerm)


  }
}

@Composable
private fun SearchView(searchTerm: String) {
  Row {
    OutlinedTextField(
      value = searchTerm,
      onValueChange = {},
      modifier = Modifier.padding(end = 8.dp),
      shape = RoundedCornerShape(16.dp))
    Button(
      onClick = { },
      modifier = Modifier.size(width = 64.dp, height = 50.dp),
      shape = RoundedCornerShape(16.dp)) {
      Image(
        painter = painterResource1(id = R.drawable.ic_outline_search_24),
        contentDescription = stringResource(R.string.search_icon)
      )
    }
  }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    SearchScreen("PAULO Coelo")
  }
}

