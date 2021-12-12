package com.gads.tunde.eduzone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.CourseListItemBinding
import com.gads.tunde.eduzone.model.Course

class CoursesAdapter() : ListAdapter<Course, CoursesAdapter.CourseViewHolder>(DiffCallback) {

    class CourseViewHolder(private var binding: CourseListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(courseItem: Course) {
            binding.courseTitle.text = courseItem.courseTitle
            binding.courseDescription.text = courseItem.courseDescription
            binding.courseImage.load(courseItem.courseImageLarge)
        }


    }

    companion object DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.courseId == newItem.courseId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CourseListItemBinding.inflate(inflater, parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val courseItem = getItem(position)
        holder.bind(courseItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(courseItem)
            }
        }
    }

    private var onItemClickListener: ((Course) -> Unit)? = null
    fun setOnItemClickListener (listener: (Course) -> Unit) {
        onItemClickListener = listener
    }


}