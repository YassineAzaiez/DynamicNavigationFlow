package com.skoove.shared.commun.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.skoove.shared.commundomain.ScreenBX
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap




/**
 * extension function that returns a view pager position and take a unit as a param
 */
fun ViewPager2.onPageChange(onChange: (Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            onChange(position)
        }
    })
}

/**
 * extension function for the Toast class that takes a string
 */
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.loadColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)


fun Context.loadDrawable(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)


fun <T> T.toStringHashMap(): HashMap<String, String> {
    val obj = Gson().toJson(this)
    return Gson().fromJson(obj, HashMap::class.java) as HashMap<String, String>
}

fun <T> T.toAnyHashMap(): HashMap<String, Any> {
    val obj = Gson().toJson(this)
    return Gson().fromJson(obj, HashMap::class.java) as HashMap<String, Any>
}


/**
 * fun that adds a zero to an integer if the integer is less then 10
 */
fun Int.formatDayAndMonthDate(): String {
    return if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}

/**
 * fun that encode Image File to base64
 * */

fun File.toBase64(): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val options = BitmapFactory.Options()
    options.inPreferredConfig = Bitmap.Config.ARGB_8888
    val bmp = BitmapFactory.decodeFile(absolutePath, options)
    bmp?.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream)
    val bytes = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(bytes, Base64.NO_WRAP)
}


/**
 * fun that create image file from URI
 * */

fun Uri.fileFromURI(activity: FragmentActivity): File {
    val cursor = activity.contentResolver.query(this, null, null, null, null)
    cursor?.moveToFirst()
    val idx: Int = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)!!
    val path = cursor.getString(idx)
    cursor.close()
    return File(path)
}


/**
 * fun for making null check on two object at the same time
 * */

fun <A, B> whileNotNull(p1: A?, p2: B?, action: (p1: A, p2: B) -> Unit) =
    p1?.let {
        p2?.let {
            action.invoke(p1, p2)
        }
    }

/**
 * fun for formatting string to timeStamp
 * */
const val BIRTH_DATE_FORMAT = "dd/MM/yyyy"
const val SALES_TRANSACTIONS_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
const val ONE_DAY_IN_MILLISECONDS_MINUS_MILLISECOND = 86399999
fun String?.toTimeStamp(endDate: Boolean = false, dateFormat: String = BIRTH_DATE_FORMAT) =
    when {
        isNullOrEmpty() -> null
        !endDate -> {
            SimpleDateFormat(dateFormat, Locale.getDefault()).parse(this)?.time
        }
        endDate -> {
            SimpleDateFormat(dateFormat, Locale.getDefault()).parse(this)?.time?.plus(
                ONE_DAY_IN_MILLISECONDS_MINUS_MILLISECOND
            )
        }
        else -> null
    }

/**
 * fun for formatting Date to selected format in GMT
 * */
const val TOP_UP_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
fun Date.toStringDate(): String {
    val dateFormat = SimpleDateFormat(TOP_UP_DATE_FORMAT, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return dateFormat.format(this)
}

/**
 * fun for formatting timeStamp to date
 * */
fun Long.toFormattedDate(): String =
    SimpleDateFormat(BIRTH_DATE_FORMAT, Locale.getDefault()).format(Date(this))

/**
 * fun for converting drawable to Bitmap
 * */

fun Int.toBitmap(context: Context): Bitmap = BitmapFactory.decodeResource(context.resources, this)

/**
 * fun for getting Minitues and seconds from millis
 * */
fun Long.asMinAndSec(): String {
    val minutes = this / 1000 / 60
    val seconds = this / 1000 % 60
    return "${minutes.formatMinSec()}:${seconds.formatMinSec()}"
}

/**
 * fun for formatting minutes or seconds
 * */
private fun Long.formatMinSec() = if (this < 10) "0$this" else this.toString()
