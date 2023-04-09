package com.example.coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        with(binding){
        buttonDownload.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
               progress.visibility = View.VISIBLE
                val url = editUri.text.toString()

                val bitmap = withContext(Dispatchers.IO) {
                    loadImage(url)
                }

                imagePreview.setImageBitmap(bitmap)
                progress.visibility = View.INVISIBLE
                }
            }
        }
    }
}
suspend fun loadImage(imageUri:String) : Bitmap {
    val url = URL(imageUri)
    val stream = url.openStream()

    return BitmapFactory.decodeStream(stream)
}