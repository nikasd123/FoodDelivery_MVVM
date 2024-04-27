package com.tz.fooddelivery.presentation.catalog.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tz.fooddelivery.R
import com.tz.fooddelivery.databinding.DishesItemBinding
import com.tz.fooddelivery.domain.models.MealItem

class MealsAdapter : ListAdapter<MealItem, MealsAdapter.DishesViewHolder>(DishesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DishesItemBinding.inflate(layoutInflater, parent, false)
        return DishesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val pizzaItem = getItem(position)
        holder.bind(pizzaItem)
    }

    inner class DishesViewHolder(private val binding: DishesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mealItem: MealItem) {
            binding.title.text = mealItem.title
            binding.description.text = mealItem.description
            binding.price.text = itemView.context.getString(R.string.price)

            Glide.with(binding.image.context)
                .load(mealItem.image)
                .into(binding.image)
        }
    }

    class DishesDiffCallback : DiffUtil.ItemCallback<MealItem>() {
        override fun areItemsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MealItem, newItem: MealItem): Boolean {
            return oldItem == newItem
        }
    }
}