package com.example.jsflo.kapod.ui.multi_apod

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.jsflo.kapod.ApodApplication
import com.example.jsflo.kapod.R
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.injection.ApodViewModelFactory
import com.example.jsflo.kapod.ui.single_apod.SingleApodActivity
import com.example.jsflo.kapod.utils.*
import kotlinx.android.synthetic.main.activity_multi_apod.*
import java.util.*

class MultiApodActivity : LifecycleActivity(), MultiApodAdapter.MultiApodListener {

    val adapter = MultiApodAdapter(listener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_apod)

        multi_apod_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        multi_apod_recycler_view.adapter = adapter

        val viewModel = ViewModelProviders.of(this, ApodViewModelFactory(application as ApodApplication))
                .get(MultiApodViewModel::class.java)
        val today = Date().toStartOfDay()
        viewModel.getApods(DateRange((today - TimeInterval.DAY * 20), today)).observe(this, android.arch.lifecycle.Observer {
            it?.let {
                adapter.swapItems(it)
            }
        })
    }

    override fun onApod(apod: Apod) {
        startActivity(SingleApodActivity.newIntent(this, apod.date))
    }
}
