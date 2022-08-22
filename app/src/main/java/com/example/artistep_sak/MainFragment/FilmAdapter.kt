package com.example.artistep_sak.MainFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artistep_sak.R
import com.example.artistep_sak.databinding.LayoutMediaListItemBinding
import com.example.exoplayer.model.MediaObject
import java.util.ArrayList

class FilmAdapter(
    private val mediaObjects: ArrayList<MediaObject>,
    private val requestManager: RequestManager
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM = 1
        const val LOADING = 0
    }


    inner class LoadingViewHolder(private val binding: LayoutMediaListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val progressBar = binding.progressBar
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.layout_media_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerViewHolder) {
            holder.onBind(mediaObjects[position], requestManager)
        } else {

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mediaObjects[position] != null) {
            ITEM
        } else {
            LOADING
        }
    }

    override fun getItemCount(): Int {
        return mediaObjects.size
    }
}



