package com.skoove.shared.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.skoove.shared.R
import com.skoove.shared.commun.extensions.hide
import com.skoove.shared.commun.extensions.loadColor
import com.skoove.shared.databinding.ComponentErrorPopupBinding

class PopUpComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ComponentErrorPopupBinding by lazy {
        ComponentErrorPopupBinding.inflate(LayoutInflater.from(context), this,false)
    }

    init {
        addView(binding.root)
    }

    fun initPopUpComponent(
        message: String,
        action: () -> Unit = {},
        buttonTitle: String = "",
        isSuccess: Boolean = false
    ) {
        if (isSuccess) {
            binding.popupMessage.setTextColor(context.loadColor(R.color.color_148250))
            binding.popupIcon.setImageResource(R.drawable.ic_validation_check)
        } else {
            binding.popupMessage.setTextColor(context.loadColor(R.color.color_ff0000))
            binding.popupIcon.setImageResource(R.drawable.ic_circle_red_x)
        }
        binding.popupMessage.text = message
        if (buttonTitle.isNotEmpty()) binding.popupSubmitBtn.text = buttonTitle
        binding.popupSubmitBtn.setOnClickListener {
            hide()
            action()
        }
    }
}