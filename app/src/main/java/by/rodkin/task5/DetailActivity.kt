package by.rodkin.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import by.rodkin.task5.databinding.ActivityDetailBinding
import coil.load

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uri = intent.getStringExtra(EXTRA_URI)
        binding.imgDetail.load(uri){
            placeholder(R.drawable.loading_animation)
        }
    }

    companion object{
        const val EXTRA_URI = "uri"
    }

    fun onClickSave(view: View) {
        
    }
}