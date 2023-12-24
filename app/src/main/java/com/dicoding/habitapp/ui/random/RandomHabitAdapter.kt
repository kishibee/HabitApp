package com.dicoding.habitapp.ui.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.databinding.PagerItemBinding

class RandomHabitAdapter(
    private val onClick: (Habit) -> Unit
) : RecyclerView.Adapter<RandomHabitAdapter.PagerViewHolder>() {

    private val habitMap = LinkedHashMap<PageType, Habit>()

    fun submitData(key: PageType, habit: Habit) {
        habitMap[key] = habit
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : PagerViewHolder {
        val pagerItemBinding = PagerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(pagerItemBinding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val pageData = habitMap[key] ?: return
        holder.bind(key, pageData)
    }

    override fun getItemCount() = habitMap.size

    private fun getIndexKey(position: Int) = habitMap.keys.toTypedArray().getOrNull(position)

    enum class PageType {
        HIGH, MEDIUM, LOW
    }

    inner class PagerViewHolder (private val binding: PagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //TODO 14 : Create view and bind data to item view

        fun bind(pageType: PageType, pageData: Habit) {
            val tvTitle = binding.pagerTvTitle
            val tvStart = binding.pagerTvStartTime
            val priority = binding.pagerPriorityLevel
            val count = binding.pagerTvMinutes
            val openCount = binding.btnOpenCountDown

            tvStart.text = pageData.startTime
            count.text = pageData.startTime
            tvTitle.text = pageData.title

            when (pageType) {
                PageType.LOW -> priority.setImageResource(R.drawable.ic_priority_low)
                PageType.MEDIUM -> priority.setImageResource(R.drawable.ic_priority_medium)
                PageType.HIGH -> priority.setImageResource(R.drawable.ic_priority_high)
            }
            openCount.setOnClickListener {
                onClick(pageData)
            }
        }
    }
}
