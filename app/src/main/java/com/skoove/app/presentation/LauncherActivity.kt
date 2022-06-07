package com.skoove.app.presentation


import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.skoove.app.R
import com.skoove.app.databinding.ActivityLauncherBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.shared.baseui.BaseViewModelActivity
import com.skoove.shared.commun.extensions.observe
import timber.log.Timber


class LauncherActivity :
    BaseViewModelActivity<MainViewModel, ActivityLauncherBinding>(MainViewModel::class.java) {
    private lateinit var navController: NavController
    override fun initViewBinding() = ActivityLauncherBinding.inflate(layoutInflater)

    override fun initViews() {
        DependenciesInit.appComponent().inject(this)
        initNavHost()
        setupActionBar()
        viewModel.login()
    }


    override fun initObservers() {
        with(viewModel) {
            observe(onLogin) { sessionId ->
                onLoginSuccess(sessionId)
            }
            observe(onError) {
                togglePopUp(it)
            }
        }
        ToolbarShared.getInstance().toolbarTitle.observe(this) {
            updateToolbarTitle(it)
        }
    }

    private fun updateToolbarTitle(title: String) {
        Timber.d("updateToolbarTitle() - title  = $title")

        binding.toolbarTitle.text = title
    }

    private fun onLoginSuccess(sessionId: String) {
        sharedPreferences.sessionId = sessionId
    }

     fun redirect() {
        when {
            sharedPreferences.isFirstLaunch -> {
                sharedPreferences.isFirstLaunch = false
                navController.navigate(R.id.ScreenAFragment)
            }

            sharedPreferences.lastFetchExperiment.isNotEmpty() && sharedPreferences.getScreensInList()
                .isEmpty() ->
                navController.redirectToScreen(sharedPreferences.lastFetchExperiment)

            sharedPreferences.lastFetchExperiment.isNotEmpty() && sharedPreferences.getScreensInList()
                .isNotEmpty() ->  navController.navigate(R.id.action_ScreenBXFragment_to_ScreenCXFragment)
            else ->navController.navigate(R.id.action_ScreenCxFragment_to_ScreenDFragment)


        }
    }

    private fun initNavHost() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as? NavHostFragment
                ?: return
        navController = navHost.navController

        navController.addOnDestinationChangedListener { _, dest, _ ->
            onNavigate(dest)
        }
    }


    private fun setupActionBar() {
        setSupportActionBar(binding.activityLauncherToolBar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }


    private fun onNavigate(dest: NavDestination) {
        val title = dest.label as String?

        title?.let {
            ToolbarShared.getInstance().updateTitle(title)
        }
    }

}