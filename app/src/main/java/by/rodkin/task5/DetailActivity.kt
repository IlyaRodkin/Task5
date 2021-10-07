package by.rodkin.task5

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import by.rodkin.task5.databinding.ActivityDetailBinding
import by.rodkin.task5.model.Cat
import coil.load

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        url = intent.getStringExtra(EXTRA_URI)
        binding.imgDetail.load(url) {
            placeholder(R.drawable.loading_animation)
        }
    }

    companion object {
        const val EXTRA_URI = "uri"
    }

    fun onClickSave(view: View) {
        try {
            val bitmap = binding.imgDetail.drawable.toBitmap()
            savePic(bitmap)
        }catch (e:Exception){
            Toast.makeText(this,"Error to save",Toast.LENGTH_SHORT).show()
        }
    }

    private fun savePic(bitmap: Bitmap) {

        val catUri = getUri()
        if (catUri != null) {
            this.contentResolver.openOutputStream(catUri).use {
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)) {
                    Toast.makeText(this,"Error to save",Toast.LENGTH_SHORT).show()
                    it?.close()
                } else {
                    Toast.makeText(this, "succeeded", Toast.LENGTH_SHORT).show()
                    it?.close()
                }
            }
        }
    }

    private fun getUri(): Uri? {
        val imageCollection =
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )
        val newImage = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "${url}.jpeg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return applicationContext?.contentResolver?.insert(imageCollection, newImage)
    }

}