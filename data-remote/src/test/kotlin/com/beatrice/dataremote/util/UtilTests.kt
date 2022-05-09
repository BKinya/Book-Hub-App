package com.beatrice.dataremote.util

import com.beatrice.dataremote.testUtil.tBook
import com.beatrice.dataremote.testUtil.tBookResult
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test


class UtilTests{
  @Test
  fun testJsonResponseToDomain() {
    val result = tBookResult.toDomain()

    assertThat(result.size, `is`(1) )
    assertThat(result[0].authors, `is`(tBook.authors))
    assertThat(result[0].title, `is`(tBook.title))
    assertThat(result[0].averageRating, `is`(tBook.averageRating))
  }
}
