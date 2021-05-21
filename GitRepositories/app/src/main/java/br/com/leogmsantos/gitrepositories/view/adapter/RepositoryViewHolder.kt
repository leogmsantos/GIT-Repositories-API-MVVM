package br.com.leogmsantos.gitrepositories.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.leogmsantos.gitrepositories.databinding.ItemGitRepositoryBinding
import br.com.leogmsantos.gitrepositories.model.GITRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RepositoryViewHolder (private val binding: ItemGitRepositoryBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun inflate(parent: ViewGroup) : RepositoryViewHolder {
            return RepositoryViewHolder(
                ItemGitRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    fun bind(repository: GITRepository, context: Context){
        binding.tvItemTitle.text = repository.name.toUpperCase()
        binding.tvItemDescription.text = repository.description
        binding.tvForkCount.text = repository.forks.toString()
        binding.tvFavouriteCount.text = repository.watchers.toString()
        binding.tvUsername.text = repository.owner?.login
        Glide.with(context)
            .load(repository.owner?.avatarUrl)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.ivAvatar)
    }

}