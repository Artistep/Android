package com.example.artistep_sak.MainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        for(i in 1 until 100){
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
        val mediaObject = MediaObject()
        mediaObject.id = 1
        mediaObject.userHandle = "User 1"
        mediaObject.title = "Item 1"
        mediaObject.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject.url =
            "https://thumbs.gfycat.com/FoolhardyMiserlyAsiantrumpetfish-mobile.mp4"
        val mediaObject2 = MediaObject()
        mediaObject2.id = 2
        mediaObject2.userHandle = "user 2"
        mediaObject2.title = "Item 2"
        mediaObject2.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject2.url =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4"
        val mediaObject3 = MediaObject()
        mediaObject3.id = 3
        mediaObject3.userHandle = "User 3"
        mediaObject3.title = "Item 3"
        mediaObject3.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject3.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"
        val mediaObject4 = MediaObject()
        mediaObject4.id = 4
        mediaObject4.userHandle = "User 4"
        mediaObject4.title = "Item 4"
        mediaObject4.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject4.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"
        val mediaObject5 = MediaObject()
        mediaObject5.id = 5
        mediaObject5.userHandle = "User 5"
        mediaObject5.title = "Item 5"
        mediaObject5.coverUrl =
            "https://www.muscleandfitness.com/wp-content/uploads/2019/04/7-Demonized-BodyBuilding-Food-Gallery.jpg?w=940&h=529&crop=1"
        mediaObject5.url =
            "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4"
        mediaObjectList.add(mediaObject)
        mediaObjectList.add(mediaObject2)
        mediaObjectList.add(mediaObject3)
        mediaObjectList.add(mediaObject4)
        mediaObjectList.add(mediaObject5)
    }
}