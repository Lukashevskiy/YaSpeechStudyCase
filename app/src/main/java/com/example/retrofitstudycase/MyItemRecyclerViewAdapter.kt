package com.example.retrofitstudycase

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

import com.example.retrofitstudycase.placeholder.PlaceholderContent.PlaceholderItem
import com.example.retrofitstudycase.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: MutableList<String>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item
        holder.contentView.background = ResourcesCompat.getDrawable(Resources.getSystem(), R.drawable.circle_shape_colored, Resources.getSystem().newTheme())
    }

    fun addItem(text: String){

        values.plus(PlaceholderItem("", text, ""))
        this.notifyItemInserted(itemCount - 1)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var contentView: TextView = binding.content
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

//    inner class ViewHolder2(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        val contentView2: TextView = binding.content
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView2.text + "'"
//        }
//    }
}