package com.gb.poplib.githubclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.poplib.githubclient.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var vb: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        with(vb) {
            btnCounter1?.setOnClickListener {
                presenter.onButtonOneClick()
            }
            btnCounter2?.setOnClickListener {
                presenter.onButtonTwoClick()
            }
            btnCounter3?.setOnClickListener {
                presenter.onButtonThreeClick()
            }
        }
    }

    override fun setTextButtonOne(text: String) {
        vb.btnCounter1?.text = text
    }

    override fun setTextButtonTwo(text: String) {
        vb.btnCounter2?.text = text
    }

    override fun setTextButtonThree(text: String) {
        vb.btnCounter3?.text = text
    }


}