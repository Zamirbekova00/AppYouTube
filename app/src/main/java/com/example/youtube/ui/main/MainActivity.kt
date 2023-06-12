package com.example.youtube.ui.main

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube.core.network.result.Status
import com.example.youtube.core.ui.BaseActivity
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.core.utils.ConnectionLiveData
import com.example.youtube.data.remote.model.Playlists
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.detail.DetailActivity
import com.example.youtube.ui.main.adapter.MainAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var adapter: MainAdapter

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun setUI() {
        super.setUI()
        adapter = MainAdapter(this::onClick)
        binding.recyclerView.adapter = adapter
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible = it

        }
        viewModel.getPlayList().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = adapter
                    adapter.addList(it.data?.items as List<Playlists.Item>)
                    viewModel.loading.postValue(false)
                }
                Status.ERROR ->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
                Status.LOADING ->{
                    viewModel.loading.postValue(true)

                }
            }
        }
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private fun onClick(item: Playlists.Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DESCRIPTION, item.snippet?.description)
        intent.putExtra(TITLE, item.snippet?.title)
        intent.putExtra(KEY_ID, item.id)
        startActivity(intent)
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            if (it) {
                binding.internetConnection.visibility = View.VISIBLE
                binding.noConnection.visibility = View.GONE
            } else {
                binding.internetConnection.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
                setupLiveData()
            }
        }
    }

    companion object {
        const val KEY_FOR_VIDEO_COUNT = "key_for_video"
        const val KEY_ID = "key_id"
        const val DESCRIPTION = "DESCRIPTION"
        const val TITLE = "TITLE"
    }
}