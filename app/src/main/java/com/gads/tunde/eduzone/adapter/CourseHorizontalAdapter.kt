package com.gads.tunde.eduzone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.CourseCardItemBinding
import com.gads.tunde.eduzone.model.Course

class CourseHorizontalAdapter: ListAdapter<Course, CourseHorizontalAdapter.CourseHorizontalViewHolder>(DiffCallback){

    class CourseHorizontalViewHolder(private val binding: CourseCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(courseItem: Course?) {
            binding.courseCardImageView.load(courseItem?.image)
            binding.courseCardTitle.text = courseItem?.title
            binding.courseCardInstructor.text = courseItem?.instructor
            binding.courseCardPrice.text = itemView.context.getString(R.string.course_price, courseItem?.price)
        }


    }

    companion object DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHorizontalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CourseCardItemBinding.inflate(inflater, parent, false)
        return CourseHorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseHorizontalViewHolder, position: Int) {
        val courseItem = getItem(position)
        holder.bind(courseItem)
    }

}