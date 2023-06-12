package com.example.youtube.detail

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.PLaylistItem

class DetailViewModel:BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PLaylistItem>> {
        return App().repository.getPlaylistItems(playlistId)
    }
}