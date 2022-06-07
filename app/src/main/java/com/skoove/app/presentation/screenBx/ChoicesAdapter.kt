package com.skoove.app.presentation.screenBx

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.skoove.app.R
import com.skoove.app.databinding.ItemChoiceBinding
import com.skoove.shared.commun.extensions.hide
import com.skoove.shared.commun.extensions.loadDrawable
import com.skoove.shared.commun.extensions.show
import com.skoove.shared.commundomain.ChoicesModel
import com.skoove.shared.commundomain.ScreenBX


class ChoicesAdapter(
    private val listResponse: List<ChoicesModel>,
    private val onChoiceSelected: (ChoicesModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var checkedPosition = -1
    override fun getItemCount() = listResponse.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ScreenBX.SCREENB2.ordinal -> OptionsHolder(
                ItemChoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ScreenBX.SCREENB1.ordinal -> ChoicesHolder(
                ItemChoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalStateException("Unknown viewType $viewType")
        }

    override fun getItemViewType(position: Int): Int {
        return if (listResponse[position].hasCheckBox) ScreenBX.SCREENB2.ordinal else ScreenBX.SCREENB1.ordinal
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is ChoicesHolder -> holder.bind(listResponse[position])
            else -> (holder as OptionsHolder).bind(listResponse[position])
        }


    inner class ChoicesHolder(view: ItemChoiceBinding) : RecyclerView.ViewHolder(view.root) {
        private val rootView = view.root
        private val choiceText = view.tvItemContent
        private val checkBox = view.ivItemCheckbox

        init {
            rootView.setOnClickListener {
                notifyItemChanged(checkedPosition) // to reset the view of the previous selected item
                checkedPosition = adapterPosition
                notifyItemChanged(checkedPosition) // to update the view of the newly selected item
                listResponse.map { it.isChoiceChecked = false }
                listResponse[adapterPosition].isChoiceChecked = true
            }
        }

        fun bind(item: ChoicesModel) {
            choiceText.text = item.response
            checkBox.hide()
            if (item.isChoiceChecked) {
                rootView.background = setTint(
                    rootView.context.loadDrawable(R.drawable.background_radius_20),
                    R.color.teal_700
                )
                onChoiceSelected.invoke(item)
            } else {
                rootView.background = setTint(
                    rootView.context.loadDrawable(R.drawable.background_radius_20),
                    R.color.color_eeeeee
                )
                onChoiceSelected.invoke(item)
            }
        }

        private fun setTint(d: Drawable?, color: Int): Drawable {
            val wrappedDrawable = DrawableCompat.wrap(d!!)
            DrawableCompat.setTint(wrappedDrawable, color)
            return wrappedDrawable
        }
    }



    inner class OptionsHolder(view: ItemChoiceBinding) : RecyclerView.ViewHolder(view.root) {
        private val choiceText = view.tvItemContent
        private val checkBox = view.ivItemCheckbox

        init {
            checkBox.setOnClickListener {
                notifyItemChanged(checkedPosition) // to reset the view of the previous selected item
                checkedPosition = adapterPosition
                notifyItemChanged(checkedPosition) // to update the view of the newly selected item
                listResponse.map { it.isChoiceChecked = false }
                listResponse[adapterPosition].isChoiceChecked = true
            }
        }

        fun bind(item: ChoicesModel) {
            choiceText.text = item.response
            checkBox.show()

            if (item.isChoiceChecked) {
                checkBox.setImageResource(R.drawable.ic_checkbox)
                onChoiceSelected.invoke(item)
            } else {
                checkBox.setImageResource(R.drawable.ic_checkbox_unchecked)
                onChoiceSelected.invoke(item)
            }
        }
    }
}