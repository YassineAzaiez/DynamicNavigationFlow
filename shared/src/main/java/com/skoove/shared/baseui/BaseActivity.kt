package com.skoove.shared.baseui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.skoove.shared.R
import com.skoove.shared.TemplateApplication
import com.skoove.shared.commun.AppSharedPreferences
import com.skoove.shared.commun.extensions.hide
import com.skoove.shared.commun.extensions.show
import com.skoove.shared.utils.ConnectionLiveData
import com.skoove.shared.widgets.PopUpComponent


abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    lateinit var binding: B

    val sharedPreferences: AppSharedPreferences by lazy { TemplateApplication.sharedComponent.appSharedPref() }

    private val container: ViewGroup by lazy { findViewById<View>(android.R.id.content) as ViewGroup }
    private lateinit var popUpComponent: PopUpComponent
    private val loading: View by lazy {
        LayoutInflater.from(this).inflate(R.layout.component_loading, container, false)
    }

    abstract fun initViewBinding(): B
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding()
        setContentView(binding.root)
        checkInternetAvailability()
        initPopUpComponent()
        initViews()
    }

    private fun checkInternetAvailability() {
        ConnectionLiveData(this).observe(this) {
            if (!it) {
                togglePopUp(getString(R.string.CommonErrorNetwork))
            }
        }
    }

    fun toggleLoading(show: Boolean) {
        if (show) {
            container.removeView(loading)
            container.addView(loading)
            loading.setOnTouchListener { _, _ -> true }
        } else {
            container.removeView(loading)
        }
    }

    private fun initPopUpComponent() {
        popUpComponent = PopUpComponent(this).apply {
            container.addView(this)
            hide()
        }
    }

    fun togglePopUp(
        message: String = "",
        action: () -> Unit = {},
        buttonTitle: String = "",
        isSuccess: Boolean = false,
        shown: Boolean = true
    ) {
        with(popUpComponent) {
            if (shown) {
                show()
                initPopUpComponent(message, action, buttonTitle, isSuccess)
            } else {
                hide()
            }
        }
    }

    fun setPopUpMessage(message: String) {
        popUpComponent.initPopUpComponent(message)
    }

}