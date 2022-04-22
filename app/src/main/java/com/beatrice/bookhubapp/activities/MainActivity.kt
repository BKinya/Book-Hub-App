package com.beatrice.bookhubapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.beatrice.bookhubapp.databinding.ActivityMainBinding
import com.beatrice.bookhubapp.viewmodels.BookViewModel
import logcat.logcat
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val bookViewModel: BookViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    bookViewModel.getBooks("Nora Roberts")
  }
}