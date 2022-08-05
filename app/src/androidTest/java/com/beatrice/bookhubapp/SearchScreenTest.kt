package com.beatrice.bookhubapp


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.beatrice.bookhubapp.ui.composables.*
import com.beatrice.bookhubapp.util.ErrorMessages
import com.beatrice.bookhubapp.util.ErrorMessages.GENERAL_API_ERROR_MESSAGE
import com.beatrice.bookhubapp.util.UiState
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

    }
}