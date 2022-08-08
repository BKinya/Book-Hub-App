package com.beatrice.bookhubapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beatrice.bookhubapp.R
import com.beatrice.bookhubapp.util.getYear
import com.beatrice.domain.models.Book
import logcat.logcat

const val BOOKS_LIST_TAG = "BooksColumn"

@Composable
fun BookListComposable(books: List<Book>, modifier: Modifier = Modifier) {
  LazyVerticalGrid(// Todo: I did this for a grid layout in large screens. Maybe I'll change
                  // if when I start optimizing for large screens
    modifier = modifier.testTag(BOOKS_LIST_TAG),
    columns = GridCells.Adaptive(minSize = 250.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    itemsIndexed(books){index, item ->
      BookRow(book = item, index)
    }
  }
}

@Composable
fun BookRow(book: Book, index: Int) {
  val isOddIndex = index % 2 != 0
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    elevation = 2.dp,
    backgroundColor = if (isOddIndex) colorResource(id = R.color.smoke_white) else Color.White
  ) {
    Row(
      modifier = Modifier
        .padding(12.dp)
        .fillMaxHeight(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // TODO: Create states for image loading
      AsyncImage(
        model = ImageRequest
          .Builder(LocalContext.current)
          .data(book.imageLink.replace("http", "https"))
          .build(),
        contentDescription = stringResource(id = R.string.book_thumbnail),
        modifier = Modifier.size(width = 100.dp, height = 140.dp),
        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
        onLoading = {
          logcat("BooksComposable") { "Image loading" }
        },
        onError = {
          logcat("BooksComposable") { "Image error ${it.result.request} \n ${it.result.throwable}" }
        },
        onSuccess = {
          logcat("BooksComposable") { "Image success" }
        },
      )
      Spacer(modifier = Modifier.size(16.dp))
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = book.title,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.SemiBold,
          fontSize = 18.sp
        )
        Text(
          text = "${book.authors}, ${book.publishingDate.getYear()}",
          fontFamily = FontFamily.SansSerif,
          fontWeight = FontWeight.Medium,// TODO: Try spannable text
          fontSize = 17.sp,
        )
        Text(
          text = "${ book.description.take(27)}...",// TODO: Fix this when you start working on the actions
          fontFamily = FontFamily.SansSerif,
          fontSize = 17.sp,
        )
        Text(
          text = "${book.pageCount} pages",
          fontFamily = FontFamily.SansSerif,
          fontSize = 17.sp,
        )
      }
    }
  }
}
//  TODO: Ratings bar. How to: https://www.youtube.com/watch?v=SmqiTrpEX-A&ab_channel=MakeitEasy

@Preview
@Composable
fun DefaultPreview1() {
  MaterialTheme {
    Surface(
      color = MaterialTheme.colors.background
    ) {
      BookListComposable(
        books = testBooks,
      )
    }
  }
}

val testBooks by lazy {
  listOf<Book>(
    tBook,
    tBook
  )
}

val tBook = Book(
  title = "Paulo Coelho: A Warrior's Life",
  pageCount = 200,
  publisher = "HarperOne",
  publishingDate = "2018-01-30",
  authors = "Paulo Coelho",
  description = "mno",
  averageRating = 4.5F,
  imageLink = "http://books.google.com/books/content?id=PU2kw-x8p2EC&printsec=frontcover&img=1&zoom=1&source=gbs_api"
)