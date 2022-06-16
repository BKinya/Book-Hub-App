
package com.beatrice.bookhubapp.util

import logcat.logcat
import java.time.LocalDate

fun String.getYear(): String {
  return try{
    val date = LocalDate.parse(this)
     date.year.toString()
  }catch (e: Exception){
    logcat("DateException"){ "is ${e.message}"}
    this
  }
}