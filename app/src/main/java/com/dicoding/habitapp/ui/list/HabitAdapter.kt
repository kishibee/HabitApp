package com.dicoding.habitapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.databinding.HabitItemBinding

class HabitAdapter(
    private val onClick: (Habit) -> Unit
) : PagedListAdapter<Habit, HabitAdapter.HabitViewHolder>(DIFF_CALLBACK) {

    //TODO 8 : Create and initialize ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val habitItemBinding = HabitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(habitItemBinding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        //TODO 9 : Get data and bind them to ViewHolder
        val habit = getItem(position) as Habit
        holder.bind(habit)
    }

    inner class HabitViewHolder(private val binding: HabitItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var getHabit: Habit
        private val ivPriority: ImageView = itemView.findViewById(R.id.item_priority_level)

        fun bind(habit: Habit) {
                binding.itemTvTitle.text = habit.title
                binding.itemTvStartTime.text = habit.startTime
                binding.itemTvMinutes.text = habit.minutesFocus.toString()
                binding
            getHabit = habit
            itemView.setOnClickListener {
                onClick(habit)
                 }
                when (getHabit.priorityLevel) {
                    "High" -> ivPriority.setImageResource(R.drawable.ic_priority_high)
                    "Medium" -> ivPriority.setImageResource(R.drawable.ic_priority_medium)
                    "Low" -> ivPriority.setImageResource(R.drawable.ic_priority_low)
                }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Habit>() {
            override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
                return oldItem == newItem
            }
        }

    }
}