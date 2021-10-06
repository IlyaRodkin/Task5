package by.rodkin.task5

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import by.rodkin.task5.adapter.AdapterCats
import by.rodkin.task5.databinding.ActivityMainBinding
import by.rodkin.task5.model.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),OnImageAction {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapterCats = AdapterCats(this)
        binding.catsList.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = adapterCats
        }
        lifecycle.coroutineScope.launch(Dispatchers.Main) {
            viewModel.flow.collectLatest { pagingData ->
                adapterCats.submitData(pagingData)
            }
        }
    }

    override fun onClickDetail(url: String) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_URI,url)
        startActivity(intent)
    }

}

interface OnImageAction {
    fun onClickDetail(url: String)
}