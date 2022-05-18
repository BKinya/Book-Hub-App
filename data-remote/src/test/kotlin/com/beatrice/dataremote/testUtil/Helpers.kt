package com.beatrice.dataremote.testUtil

import java.io.File
import com.google.common.io.Resources.getResource

fun                                                                                                            getJson(path: String): String {
  val uri = getResource(path)
  val file = File(uri.path)
  return String(file.readBytes())
}