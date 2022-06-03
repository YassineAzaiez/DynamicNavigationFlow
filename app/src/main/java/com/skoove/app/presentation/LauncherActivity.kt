package com.skoove.app.presentation


import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.skoove.app.R
import com.skoove.app.databinding.ActivityLauncherBinding
import com.skoove.app.di.component.DependenciesInit
import com.skoove.shared.commundomain.ScreenDataModel
import com.skoove.shared.baseui.BaseViewModelActivity
import com.skoove.shared.commun.extensions.observe


class LauncherActivity :
    BaseViewModelActivity<MainViewModel, ActivityLauncherBinding>(MainViewModel::class.java) {
    private lateinit var appBarConfiguration: AppBarConfiguration
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
            observe(onError) { togglePopUp(it) }
        }
    }

    private fun onLoginSuccess(sessionId: String) {
        sharedPreferences.sessionId = sessionId
        redirect()
    }

    private fun redirect() {
        when {
            sharedPreferences.isFirstLaunch -> {
                sharedPreferences.isFirstLaunch = true
                navController.navigate(R.id.ScreenAFragment)
            }

            sharedPreferences.getScreensInList().isEmpty() -> navController.redirectTo(sharedPreferences.lastFetchExperiment)

        }
    }

    private fun initNavHost() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as? NavHostFragment
                ?: return
        navController = navHost.navController

        navController.addOnDestinationChangedListener { _, dest, arguments ->
            val screenData = ScreenDataModel(dest.label.toString(), arguments)
            sharedPreferences.putScreeInList(screenData)
        }
    }


    //Setting Up ActionBar with NavController
    //Pass the Ids of topLevel destinations in AppBarConfiguration
    private fun setupActionBar() {
        setSupportActionBar(binding.activityLauncherToolBar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.ScreenAFragment,
                R.id.ScreenB1Fragment,
                R.id.ScreenB2Fragment,
                R.id.ScreenB3Fragment,
                R.id.ScreenC1Fragment,
                R.id.ScreenC2Fragment,
                R.id.ScreenDFragment,

                )

        )


        setupActionBarWithNavController(navController, appBarConfiguration)

    }


}