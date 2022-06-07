package com.skoove.shared.baseui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.skoove.shared.commun.AppSharedPreferences

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    protected lateinit var activity: BaseActivity<*>
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var interceptBackClick = false
    protected lateinit var sharedPreferences: AppSharedPreferences


    abstract fun initViews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as BaseActivity<*>
        sharedPreferences = activity.sharedPreferences

        activity.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (interceptBackClick) {
                    onBackPressCustomAction()
                } else {
                    isEnabled = false
                    activity.onBackPressed()
                }
            }
        })
    }

    /**
     * Method to override the backPressNative behavior.
     * If we want to keep default onBackPress           : Don't override method
     * If we want to disable backPress                  : "disableDefaultBackPress" = true
     * If we want to add custom action on BackPressed   : "disableDefaultBackPress" = true && override "onBackPressCustomAction()" with custom code inside
     *
     */

    open fun onBackPressCustomAction() {
        // override inside fragment that have a custom backPress action
    }

    fun disableDefaultBackPress(disable: Boolean) {
        interceptBackClick = disable
    }



    protected fun toggleLoading(show: Boolean) {
        if (isAdded) {
            activity.toggleLoading(show)
        }
    }

    protected fun togglePopUp(
        message: String = "",
        action: () -> Unit = {},
        buttonTitle: String = "",
        isSuccess: Boolean = false,
        show: Boolean = true
    ) {
        if (isAdded) activity.togglePopUp(message, action, buttonTitle, isSuccess, show)
    }

    protected fun setPopUpMessage(message: String) {
        if (isAdded) activity.setPopUpMessage(message)
    }

}