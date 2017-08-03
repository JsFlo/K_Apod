package com.example.jsflo.kapod.ui.single_apod

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.jsflo.kapod.ApodApplication
import com.example.jsflo.kapod.R
import com.example.jsflo.kapod.injection.ApodViewModelFactory
import com.example.jsflo.kapod.utils.loadImg
import com.example.jsflo.kapod.utils.setTextOrHide
import com.example.jsflo.kapod.utils.toPrettyFormat
import com.example.jsflo.kapod.utils.toStartOfDay
import kotlinx.android.synthetic.main.single_apod_activity.*
import kotlinx.android.synthetic.main.view_apod_text.*
import java.util.*

class SingleApodActivity : LifecycleActivity() {
    companion object {
        val EXTRA_DATE = "EXTRA_DATE"
        fun newIntent(context: Context, date: Date): Intent {
            return Intent(context, SingleApodActivity::class.java)
                    .putExtra(EXTRA_DATE, date)
        }
    }

    lateinit var mDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_apod_activity)

        val bundle: Bundle? = savedInstanceState ?: intent.extras
        mDate = (bundle?.get(EXTRA_DATE) ?: Date()) as Date

        val viewModel = ViewModelProviders.of(this, ApodViewModelFactory(application as ApodApplication))
                .get(SingleApodViewModel::class.java)
        viewModel.getApod(mDate.toStartOfDay()).observe(this, Observer {
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
