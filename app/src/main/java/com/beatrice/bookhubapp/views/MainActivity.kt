package com.beatrice.bookhubapp.views

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import com.beatrice.bookhubapp.views.composables.SearchScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
  private val bookViewModel: BookViewModel by viewModel()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface(color = MaterialTheme.colors.background) {

          SearchScreen(bookViewModel = bookViewModel)
        }
      }
    }

  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    Surface(color = MaterialTheme.colors.background) {
      SearchScreen()
    }
  }
}

