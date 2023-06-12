package com.example.youtube.ui.main

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.Playlists

open class MainViewModel : BaseViewModel() {

    fun getPlayList(): LiveData<Resource<Playlists>> {
        return App().repository.getPlaylists()
    }
}