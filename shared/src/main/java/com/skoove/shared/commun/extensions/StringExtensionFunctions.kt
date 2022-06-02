package com.skoove.shared.commun.extensions

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.util.Base64
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * extension function to dial a phone number
 */
fun String.makeCall(activity: Activity) {
    if (length >= 8)
        activity.startActivity(
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${trim().substring(0, 8)}")
            )
        )
}

fun String.makeServiceNumberCall(activity: Activity) {
    if (length == 7) {
        activity.startActivity(
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${trim().substring(0, 7)}")
            )
        )
    }
}

/**
 * extension function to send email
 * */
fun String.sendEmail(activity: Activity) {
    activity.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$this")))
}

/**
 * extension function to open link in browser
 * */
fun String.openLink(activity: Activity) {
    activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this)))
}

fun String.convertLocationToDouble(): Double {
    return if (this == ".") 0.0
    else this.toDouble()
}

fun String.isEmailInvalid() = !android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPhoneInvalid() = !android.util.Patterns.PHONE.matcher(this).matches()

fun String.isNameInvalid() = !matches("^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,2}\$".toRegex())

fun String.containsOnlyLetters() = none { it !in 'A'..'Z' && it !in 'a'..'z' }

fun EditText.showError(message: String) {
    error = message
    requestFocus()
}

/**
 * fun that decode base64 to bitmap
 * */

fun String.fromBase64(): Bitmap? {
    val decodedString: ByteArray = Base64.decode(this, Base64.NO_WRAP)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size) ?: null
}

/**
 * fun for encoding String to Base64
 * */
fun String.encode64(): String = Base64.encodeToString(toByteArray(charset("UTF-8")), Base64.DEFAULT)

/**
 * fun for decoding String from Base64
 * */

fun String.decode64() = Base64.decode(this, Base64.DEFAULT).toString(charset("UTF-8"))

/**
 * fun for checking how many days remaining for fahes car inspection
 * */

const val FAHES_GUEST_DATE_FORMAT = "dd-MM-yyyy"
const val FAHES_USER_DATE_FORMAT = "yyyy-MM-dd"
fun String.daysToInspection(dateFormat: String): Boolean {
    val date = SimpleDateFormat(dateFormat, Locale.getDefault()).parse(this) ?: Date()
    val cal = Calendar.getInstance()
    cal.add(Calendar.MONTH, 1)
    return TimeUnit.MILLISECONDS.toDays(date.time - cal.timeInMillis) <= 30
}

/**
 * fun for parsing arabic digits
 * */
fun String.parseArabic() =
    StringBuilder().apply {
        toCharArray().forEach {
            when {
                Character.isDigit(it) -> append(Character.getNumericValue(it))
                it == '٫' -> append(".")
                else -> append(it)
            }
        }
        toString()
    }

/**
 * get the extension of a file from a url
 */
fun String.setFileExtension(extension: String) =
    if (contains(".")) substring(lastIndexOf(".")) else extension

/**
 * fun for display text from html
 * */

fun String.textInHtml(): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }

/**
 * get spannable string and set the specific spans to it
 */
fun String.getSpannableString(vararg any: Any): SpannableString {
    val spannableString = SpannableString(this)
    any.forEach {
        spannableString.setSpan(
            it,
            0,
            spannableString.length,
            0
        )
    }
    return spannableString
}

/**
 * fun for formatting String Date to selected format
 * */
fun String.formatStringDate(): String {
    val dateFormat = SimpleDateFormat(SALES_TRANSACTIONS_FORMAT, Locale.getDefault())
    val date = dateFormat.parse(this)
    return SimpleDateFormat(BIRTH_DATE_FORMAT, Locale.getDefault()).format(date!!)
}

/**
 * fun to get String value or return empty
 **/

fun String?.valueOrEmpty() = this?:""