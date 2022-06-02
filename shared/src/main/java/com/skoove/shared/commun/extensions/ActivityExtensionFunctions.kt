package com.skoove.shared.commun.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skoove.shared.R
import com.skoove.shared.baseui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlin.reflect.KClass


/**
 * extension function to hide keyboard in activity
 */
fun Activity.hideKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}

/**
 * extension function to hide keyboard in fragment
 */
fun Fragment.hideKeyboard() {
    if (activity?.currentFocus != null) {
        val inputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, 0)
    }
}

/**
 * extension function to show keyboard in activity
 */
fun Activity.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

/**
 * extension function to observe liveData
 */

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this) { it?.let { t -> observer(t) } }
}

/**
 * extension function that creates an instance of a view model with viewModel factory
 */
fun <VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory, model: KClass<VM>
) = ViewModelProvider(this, provider).get(model.java)

/**
 * extension function that creates an instance of a view model with viewModel factory from a fragment
 */
fun <VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory, model: KClass<VM>
) = ViewModelProvider(this, provider).get(model.java)


fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

/**
 * extension function to replace fragment
 */
fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    container: Int,
    addToBackStack: Boolean = false
) {
    fragment.activity?.hideKeyboard()
    val className = fragment.javaClass.name
    val fragmentTransaction = supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, className)
    if (addToBackStack) {
        fragmentTransaction.addToBackStack(className)
    }
    fragmentTransaction.commit()
}

/**
 * extension function to add fragment
 */
fun FragmentActivity.addFragment(fragment: Fragment, container: Int) {
    fragment.activity?.hideKeyboard()
    val className = fragment.javaClass.name
    supportFragmentManager
        .beginTransaction()
        .add(container, fragment, className)
        .addToBackStack(null)
        .commit()
}

/**
 * extension function to start activities
 * with extras : startActivity<SomeActivity> {putExtra(KEY, value)}
 * without extras : startActivity<SomeActivity>()
 */
internal inline fun <reified T : Activity> Activity.startActivity(extras: Bundle) {
    startActivity(Intent(this, T::class.java).putExtras(extras))
}

inline fun <reified T : Activity> Activity.startActivity(isFinish: Boolean = true) {
    startActivity(Intent(this, T::class.java))
    if (isFinish) finish()
}


fun Activity.showSnackbar(view: View?, message: String, isError: Boolean = false) {
    if (view == null) return
    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        snackbar.setBackgroundTint(loadColor(R.color.color_d32f2f))
            .setTextColor(loadColor(R.color.colorWhite))
            .show()
    else
        snackbar.setBackgroundTint(loadColor(R.color.colorPrimaryDark))
            .setTextColor(loadColor(R.color.colorWhite))
            .show()
}




/**
 * fun for previewing pdf file in the browser
 * */
const val TEXT_HTML = "text/html"
const val GOOGLE_DOCS_VIEWER = "https://docs.google.com/viewer?url="
fun FragmentActivity.previewPDf(url: String) {
    Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(Uri.parse("$GOOGLE_DOCS_VIEWER$url"), TEXT_HTML)
        startActivity(this)
    }
}

/**
 * extension function to replace fragment
 */
fun FragmentActivity.replaceFragment(
    fragment: BaseFragment<*>,
    container: Int,
    addToBackStack: Boolean = true,
    recreateFragment: Boolean = false
) {
    fragment.activity?.hideKeyboard()
    val className = fragment.javaClass.canonicalName
    val manager = supportFragmentManager

    if (isSameFragment(className, container) && !recreateFragment) return

    val fragmentTransaction = manager.beginTransaction()
        .replace(container, fragment, className)

    if (addToBackStack) {
        fragmentTransaction.addToBackStack(className)
    }

    fragmentTransaction.commit()

}

private fun FragmentActivity.isSameFragment(fragmentName: String? = "", container: Int) =
    fragmentName == supportFragmentManager.findFragmentById(container)?.javaClass?.canonicalName


/**
 *
 * As a FragmentTransaction is treated as a single atomic set of operations,
 * calls to both detach and attach on the same fragment instance in the same transaction effectively cancel each other out.
 * Use separate transactions, separated by executePendingOperations() if using commit(), if you want to detach and then
 * immediately re-attach a fragment.
 */
fun FragmentActivity.relaunchFragment(fragmentId: Int) {
    val currentFragment = supportFragmentManager.findFragmentById(fragmentId)
    val detachTransaction = supportFragmentManager.beginTransaction()
    val attachTransaction = supportFragmentManager.beginTransaction()

    currentFragment?.let {
        detachTransaction.detach(it)
        attachTransaction.attach(it)
        detachTransaction.commit()
        attachTransaction.commit()
    }
}