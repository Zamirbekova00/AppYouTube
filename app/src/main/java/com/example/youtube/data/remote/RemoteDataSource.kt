package com.example.youtube.data.remote

import com.example.youtube.BuildConfig
import com.example.youtube.core.network.BaseDataSource
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.utils.channelId
import com.example.youtube.core.utils.part
import com.example.youtube.data.remote.model.PLaylistItem
import com.example.youtube.data.remote.model.Playlists

class RemoteDataSource : BaseDataSource() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylists(): Resource<Playlists> {
        return getResult {
            apiService.getPlayList(
                BuildConfig.API_KEY,
                part,
                channelId,
                30
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Resource<PLaylistItem> {
        return getResult {
            apiService.getPlaylistItems(
                BuildConfig.API_KEY,
                part,
                playlistId,
                30
            )
        }
    }
}