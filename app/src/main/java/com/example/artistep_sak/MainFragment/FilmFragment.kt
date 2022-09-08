package com.example.artistep_sak.MainFragment

import android.content.ContentValues.TAG
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

import com.example.artistep_sak.R
import com.example.artistep_sak.databinding.FragmentFilmBinding
import com.example.exoplayer.model.MediaObject
import com.example.exoplayer.utils.ExoPlayerRecyclerView
import java.util.ArrayList

class FilmFragment : Fragment(){


    //썸네일,사이즈 추출 라이브러리 MediaMetaDataRetriever
    private  var _binding:FragmentFilmBinding?=null
    private val binding get()=_binding!!

    var mRecyclerView: ExoPlayerRecyclerView? = null
    private val mediaObjectList = ArrayList<MediaObject>()
    private var mAdapter: FilmAdapter? = null
    var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.title_bar, menu)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        //Video Size 추출
//        val retriever = MediaMetadataRetriever()
//        retriever.setDataSource(Uri.parse("https://thumbs.gfycat.com/FoolhardyMiserlyAsiantrumpetfish-mobile.mp4").toString(), HashMap<String,String>())
//        val originWidth = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
//        val originHeight= retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
//        retriever.setDataSource(Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4").toString(), HashMap<String,String>())
//        val originWidth1 = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
//        val originHeight1= retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
//        retriever.release()
//
//        Log.d(TAG, "playVideo: size: $originWidth")
//        Log.d(TAG, "playVideo: size: $originHeight")
//
//        Log.d(TAG, "playVideo: size: $originWidth1")
//        Log.d(TAG, "playVideo: size: $originHeight1")
        for(i in 1 until 10){
            prepareVideoList()
        }

        mRecyclerView!!.setMediaObjects(mediaObjectList)
        mAdapter = FilmAdapter(mediaObjectList, initGlide())
        mRecyclerView!!.adapter = mAdapter
        mRecyclerView!!.smoothScrollToPosition(1)
        binding.exoPlayerRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.exoPlayerRecyclerView.layoutManager as LinearLayoutManager

                if(!isLoading){
                    if(!binding.exoPlayerRecyclerView.canScrollVertically(1)){
                        isLoading = true
                    prepareVideoList()
                    }
                }
            }
        })

    }
    private fun initView() {
        mRecyclerView=getView()?.findViewById<ExoPlayerRecyclerView>(R.id.exoPlayerRecyclerView)
        mRecyclerView!!.setLayoutManager(
            LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL,false)
        )
    }
    override fun onDestroy() {
        if (mRecyclerView != null) {
            mRecyclerView!!.releasePlayer()
        }
        super.onDestroy()
    }


    private fun initGlide(): RequestManager {
        val options = RequestOptions()
        return Glide.with(this)
            .setDefaultRequestOptions(options)
    }
    private fun prepareVideoList() {
//        Video Size Export

        val mediaObject = MediaObject()
        //Test 세로로 긴영상
        mediaObject.id = 1
        mediaObject.userHandle = "User 1"
        mediaObject.title = "세로 1"
        mediaObject.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject.url =
            "https://thumbs.gfycat.com/FoolhardyMiserlyAsiantrumpetfish-mobile.mp4"

        //가로로 긴영상
        val mediaObject2 = MediaObject()
        mediaObject2.id = 2
        mediaObject2.userHandle = "user 2"
        mediaObject2.title = "Item 2"
        mediaObject2.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject2.url =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"

        mediaObjectList.add(mediaObject)
        mediaObjectList.add(mediaObject2)
    }
}