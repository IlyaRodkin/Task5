package by.rodkin.task5.adapter

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.rodkin.task5.DetailActivity
import by.rodkin.task5.OnImageAction
import by.rodkin.task5.R
import by.rodkin.task5.databinding.CatItemBinding
import by.rodkin.task5.model.Cat
import coil.load

class AdapterCats(val context: Context) : PagingDataAdapter<Cat, CatHolder>(REPO_COMPARATOR) {

    private val listener = context as OnImageAction

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatItemBinding.inflate(inflater, parent, false)

        return CatHolder(binding)
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val url = getItem(position)?.url
        holder.bind(url)
        holder.itemView.setOnClickListener {
            listener.onClickDetail(url!!)
        }
    }


}

class CatHolder(private val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(img: String?) {
        binding.image.load(img) {
            placeholder(R.drawable.loading_animation)
        }
    }
}