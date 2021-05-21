package br.com.leogmsantos.gitrepositories.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.leogmsantos.gitrepositories.model.GITRepository

class RepositoryAdapter (private val context: Context, private var repositories: ArrayList<GITRepository>) : RecyclerView.Adapter<RepositoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.inflate(
            parent
        )
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position], context)
    }

    fun updateRepositories(repositories: ArrayList<GITRepository>){
        this.repositories.addAll(repositories)
        notifyDataSetChanged()
    }

    fun clearList(){
        this.repositories.clear()
        notifyDataSetChanged()
    }
}