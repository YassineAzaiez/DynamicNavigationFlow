package com.skoove.shared.commun.extensions

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.RectF
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.skoove.shared.R
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * extension function that make any view visible
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * extension function that make any view invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}


/**
 * extension function that hide any view (gone)
 */
fun View.hide() {
    visibility = View.GONE
}

fun EditText.value() = text.toString().trim()

/**""
 * fun for performing click on group of views
 * */
fun Group.addOnClickListener(listener: (view: View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

/**
 * fun for setting text color
 * */
fun TextView.textColor(color: Int) {
    setTextColor(context.loadColor(color))
}

/**
 *  fun for getting child view from included layout
 * */
fun <T : View> View.getChildById(id: Int): T = findViewById(id)

@Suppress("DEPRECATION")
fun View.setBackgroundTint(activity: Activity, color: Int) {
    background.setColorFilter(activity.loadColor(color), PorterDuff.Mode.SRC_ATOP)
}

fun ImageView.setColorTint(activity: Activity, color: Int) {
    setColorFilter(activity.loadColor(color), PorterDuff.Mode.MULTIPLY)
}

/**
 * add swipe to delete behavior to recyclerView
 * */
fun simpleItemTouchCallback(
    bgColor: Int = R.color.color_d32f2f,
    textColor: Int = R.color.colorWhite,
    icon: Int = R.drawable.forbidden,
    message: String = "Deactivate",
    onSwipe: (Int) -> Unit
) = object :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipe(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val itemView = viewHolder.itemView
            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
            val width = height / 3
            val bg = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = itemView.context.loadColor(bgColor)
            }
            val textStyle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = itemView.context.loadColor(textColor)
                textSize = 36f
                textAlign = Paint.Align.CENTER
            }
            if (dX < 0) {
                val background = RectF(
                    itemView.right.toFloat() + dX,
                    itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat()
                )
                val iconDest = RectF(
                    itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width,
                    itemView.right.toFloat() - width, itemView.bottom.toFloat() - width
                )
                c.drawRect(background, bg)
                c.drawBitmap(icon.toBitmap(itemView.context), null, iconDest, bg)
                c.drawText(
                    message,
                    itemView.right.toFloat() - 220,
                    background.centerY() + 10,
                    textStyle
                )
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}

/**
 * extension function for loading the user profile picture
 */
fun ImageView.loadPicture(
    picture: String?,
    placeholder: Int = R.drawable.ic_user_placeholder,
    activity: Activity? = null
) {
    if (picture.isNullOrEmpty()) {
        activity?.let { setColorFilter(it.loadColor(R.color.colorWhite)) }
        setImageResource(placeholder)
    } else {
        activity?.let { setColorFilter(it.loadColor(android.R.color.transparent)) }
        load(picture) {
            transformations(CircleCropTransformation())
            placeholder(placeholder)
            error(placeholder)
        }
    }
}

const val DRAG_TOLERANCE = 16
const val DURATION_MILLIS = 250L

/**
 * extension function to make any view draggable
 * takes as a param a higher order function that returns the view
 */
fun View.makeDraggable(
    onPositionChanged: (View) -> Unit
) {
    var widgetInitialX = 0f
    var widgetDX = 0f
    var widgetInitialY = 0f
    var widgetDY = 0f

    setOnTouchListener { v, event ->
        val viewParent = v.parent as View
        val parentHeight = viewParent.height
        val parentWidth = viewParent.width
        val defaultMargin = (parentWidth / 25).toFloat()
        val xMax = (parentWidth - v.width) - defaultMargin
        val xMiddle = parentWidth / 2
        val yMax = parentHeight - v.height

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                widgetDX = v.x - event.rawX
                widgetDY = v.y - event.rawY
                widgetInitialX = v.x
                widgetInitialY = v.y
            }
            MotionEvent.ACTION_MOVE -> {
                var newX = event.rawX + widgetDX
                newX = max(defaultMargin, newX)
                newX = min(xMax, newX)
                v.x = newX

                var newY = event.rawY + widgetDY
                newY = max(defaultMargin, newY)
                newY = min(yMax.toFloat(), newY)
                v.y = newY
                onPositionChanged(v)
            }
            MotionEvent.ACTION_UP -> {
                if (event.rawX >= xMiddle) {
                    v.animate().x(xMax)
                        .setDuration(DURATION_MILLIS)
                        .setUpdateListener { onPositionChanged(v) }
                        .start()
                } else {
                    v.animate().x(defaultMargin).setDuration(DURATION_MILLIS)
                        .setUpdateListener { onPositionChanged(v) }
                        .start()
                }
                if (abs(v.x - widgetInitialX) <= DRAG_TOLERANCE && abs(v.y - widgetInitialY) <= DRAG_TOLERANCE) {
                    performClick()
                }
            }
            else -> return@setOnTouchListener false
        }
        true
    }
}

/**
 * fun for handling editText text change
 * */

fun EditText.onChangeListener(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //no action needed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //no action needed
        }
    })
}