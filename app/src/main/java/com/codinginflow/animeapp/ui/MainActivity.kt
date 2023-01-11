package com.codinginflow.animeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.codinginflow.animeapp.R
import com.codinginflow.animeapp.data.DataStoreRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

        val dataStoreRepository = DataStoreRepository(this)

        lifecycleScope.launch {
            val result = dataStoreRepository.read("key")
            if (result == null) {
                val intent = Intent(this@MainActivity, FirstTimeActivity::class.java)
                startActivity(intent)
            } else {
                setContentView(R.layout.activity_main)
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        finishAffinity()
    }
}