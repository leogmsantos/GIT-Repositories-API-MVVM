package br.com.leogmsantos.gitrepositories.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.leogmsantos.gitrepositories.databinding.ItemGitRepositoryBinding
import br.com.leogmsantos.gitrepositories.model.GITRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RepositoryViewHolder (private val binding: ItemGitRepositoryBinding) : RecyclerView.ViewHolder(binding.root){

    companion object {
        fun inflate(parent: ViewGroup) : RepositoryViewHolder {
            return RepositoryViewHolder(
                ItemGitRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    fun bind(repository: GITRepository, context: Context){
        binding.tvItemTitle.text = repository.name.toUpperCase(Locale.ROOT)
        binding.tvItemDescription.text = repository.description
        binding.tvForkCount.text = repository.forks.toString()
        binding.tvFavouriteCount.text = repository.watchers.toString()
        binding.tvUsername.text = repository.owner.login
        Glide.with(context)
            .load(repository.owner.avatarUrl)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.ivAvatar)

        binding.tvLanguageValue.text = repository.language
        binding.tvCreatedAtValue.text = convertData(repository.createdAt)
        binding.tvLastUpdateValue.text = convertData(repository.lastUpdate)

        binding.ivExpand.setOnClickListener {
            binding.ivExpand.animate().rotationBy(180F).setDuration(300).start()
            binding.detailsContainer.visibility = if (binding.detailsContainer.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        binding.ivClone.setOnClickListener {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(repository.cloneUrl)))
        }
    }

    private fun convertData(data: String): String? {
        val ts = data.split("T").toTypedArray()
        val date = ts[0]
        var dataFormatada = ""
        val fromUser = SimpleDateFormat("dd/MM/yyyy")
        val myFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            dataFormatada = fromUser.format(myFormat.parse(date))
        } catch (e: ParseException) {
            Log.d(
                this.javaClass.name,
                e.message,
                e
            )
        }
        return dataFormatada
    }
}