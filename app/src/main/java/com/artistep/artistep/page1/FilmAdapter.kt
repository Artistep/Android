package com.artistep.artistep.page1

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artistep.artistep.databinding.ItemFilmBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.trackselection.MappingTrackSelector
import com.google.android.exoplayer2.util.EventLogger
import com.google.android.exoplayer2.video.VideoSize

class FilmAdapter(val context: Context, val filmList: MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var playWhenReady = true
    private var currentWindow = 0
    var width : Int = 0
    var height : Int = 0
    var pause = false

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        Log.d("Adapter","view rv attached")

    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        Log.d("Adapter","view rv detached")
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("Adapter","view ${holder.layoutPosition} attached")
        if (holder is ItemViewHolder) {
            holder.player!!.seekTo(holder.playbackPosition)
            holder.binding.filmPlayerView.onResume()
//            holder.player?.stop()

        }else{}
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d("Adapter","view ${holder.layoutPosition} detached")

        if (holder is ItemViewHolder) {
            holder.playbackPosition = holder.player!!.currentPosition
            holder.player?.pause()

        }else{}
    }

    inner class ItemViewHolder(val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var playbackPosition = 0L
        lateinit var itemUri: String
        var player: ExoPlayer? = null
        fun setItem(item: String) {
            with(binding) {
//                //item test
//                binding.itemVideoNum.text = item

                itemUri = item
                initializePlayer()
            }
        }

        private fun initializePlayer() {
            player = ExoPlayer.Builder(context)
                .build()
                .also { exoPlayer ->
                    binding.filmPlayerView.player = exoPlayer
                    val mediaItem = MediaItem.fromUri(itemUri)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.playWhenReady = playWhenReady
                    exoPlayer.seekTo(currentWindow, playbackPosition)
                    exoPlayer.prepare()
                }

            player!!.let { it ->
                with(it) {
                    addAnalyticsListener(object :
                        EventLogger(trackSelector as MappingTrackSelector) {

                        override fun onVideoSizeChanged(
                            eventTime: AnalyticsListener.EventTime,
                            videoSize: VideoSize
                        ) {
                            super.onVideoSizeChanged(eventTime, videoSize)
                            Log.d(
                                "TAG_DEBUG", "decoder Name : ${
                                    videoSize.width
                                }x${videoSize.height}"
                            )
                            width = videoSize.width
                            height = videoSize.height
                            binding.resolution.text = "$width x $height"

                        }
                    })
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.itemUri = filmList[position]
            holder.setItem(filmList[position]!!)
            holder.binding.filmPlayerView.setOnClickListener {
                if (!pause) {
                    pause = true
                    holder.binding.filmPlayerView.player?.pause()
                } else {
                    holder.binding.filmPlayerView.player?.play()
                    pause = false
                }
            }
        } else {

        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
}