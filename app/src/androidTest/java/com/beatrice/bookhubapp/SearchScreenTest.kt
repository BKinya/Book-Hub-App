package com.beatrice.bookhubapp


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.beatrice.bookhubapp.ui.composables.*
import com.beatrice.bookhubapp.util.ErrorMessages
import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.UiState
import com.beatrice.domain.models.Book
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     *  UI test search feature
     *  1. Verify search button is disabled
     *  2. Enter text in search input field
     *  3. Verify search button is enabled
     *  4. Clear text
     *  5, Verify search button is disabled
     *  6. Verify clear button does not exist
     */
    @Test
    fun testOne() {
        composeTestRule.setContent {
            SearchInputComposable()
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("SearchScreen")
        val searchButton = composeTestRule.onNodeWithTag(BUTTON_TAG)
        searchButton
            .assertIsDisplayed()
            .assertIsNotEnabled()
        val inputField = composeTestRule.onNodeWithTag(INPUT_TAG)
        inputField.assertIsDisplayed().performTextInput("Range: Why....")
        searchButton.assertIsEnabled()
        val clearButton = composeTestRule.onNodeWithTag(CLEAR_BUTTON_TAG)
        clearButton.assertIsDisplayed().performClick()
        searchButton.assertIsNotEnabled()
        clearButton.assertDoesNotExist()
    }

    /**
     * Verify the progress indicator is shown when the [UiState] is [Loading]
     */
    @Test
    fun testTwo() {
        composeTestRule.setContent { BooksStateComposable(booksUiState = UiState.Loading()) }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("BookStateComposable")
        composeTestRule.onNodeWithTag(PROGRESS_INDICATOR_TAG).assertIsDisplayed()
    }

    /**
     * Verify the Error UI is shown when [UiState] is [Error]
     */
    @Test
    fun testThree() {
        composeTestRule.setContent {
            BooksStateComposable(booksUiState = UiState.Error(GENERAL_API_ERROR_MESSAGE))
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("ErrorComposable")
        composeTestRule.onNodeWithTag(ERROR_UI_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText(GENERAL_API_ERROR_MESSAGE).assertIsDisplayed()
    }

    /**
     * Verify the UI shows a list of books fetched from the API
     */
    @Test
    fun testFour(){
        val uiState = UiState.Success(
            data = listOf(
                Book(
                    title = "Paulo Coelho: A Warrior's Life",
                    pageCount = 200,
                    publisher = "HarperOne",
                    publishingDate = "2018-01-30",
                    authors = "Paulo Coelho",
                    description = "mno",
                    averageRating = 4.5F,
                    imageLink = "http://books.google.com/books/content?id=PU2kw-x8p2EC&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                ),
                Book(
                    title = "I know why the caged bird sings",
                    pageCount = 300,
                    publisher = "HarperOne1",
                    publishingDate = "2018-01-23",
                    authors = "Maya Angelou",
                    description = "asdfasdfasdf",
                    averageRating = 4.8F,
                    imageLink = "http://books.google.com/books/content?id=PU2kw-x8p2EC&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                )
            )
        )

        composeTestRule.setContent {
            BooksStateComposable(booksUiState = uiState)
        }

        composeTestRule.onRoot().printToLog("BooksList")
        composeTestRule.onNode(hasScrollAction() and hasTestTag(BOOKS_LIST_TAG)).assertIsDisplayed()
        val titleOne = uiState.data?.get(0)?.title
        val titleTwo = uiState.data?.get(1)?.title
        composeTestRule.onNodeWithText(titleOne!!).assertIsDisplayed()
        composeTestRule.onNodeWithText(titleTwo!!).assertIsDisplayed()


    }
}