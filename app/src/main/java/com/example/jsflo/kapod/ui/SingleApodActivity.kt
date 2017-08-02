package com.example.jsflo.kapod.ui

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.jsflo.kapod.ApodApplication
import com.example.jsflo.kapod.R
import com.example.jsflo.kapod.utils.loadImg
import com.example.jsflo.kapod.utils.setTextOrHide
import com.example.jsflo.kapod.utils.toPrettyFormat
import kotlinx.android.synthetic.main.single_apod_activity.*
import kotlinx.android.synthetic.main.view_apod_text.*
import java.util.*

class SingleApodActivity : LifecycleActivity() {
    lateinit var mDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_apod_activity)

        mDate = Date()

        val application = application as ApodApplication
        val viewModel = ViewModelProviders.of(this)
                .get(SingleApodViewModel::class.java)

        application.apodComponent.inject(viewModel)
        viewModel.getApod(mDate).observe(this, Observer {
            it?.let {
                hero_image.loadImg(it.url)
                apod_title.text = it.title
                apod_explanation.text = it.explanation
                apod_copyright.setTextOrHide(it.copyright)
                apod_date.text = it.date.toPrettyFormat()
            }
        })
    }
}
