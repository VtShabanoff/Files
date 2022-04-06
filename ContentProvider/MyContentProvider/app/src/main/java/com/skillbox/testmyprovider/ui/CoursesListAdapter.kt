package com.skillbox.testmyprovider.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skillbox.testmyprovider.R
import com.skillbox.testmyprovider.domain.Course

class CoursesListAdapter(
    private val onItemClickListener: (course: Course) -> Unit
) : ListAdapter<Course, CoursesListAdapter.CourseHolder>(DiffUtilCourse()) {

    class DiffUtilCourse : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }

    class CourseHolder(private val view: View, onItemClickListener: (course: Course) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val titleVH = view.findViewById<TextView>(R.id.title_tv)
        private lateinit var courseVH: Course

        init {
            view.setOnClickListener {
                onItemClickListener(courseVH)
            }
        }

        fun bind(course: Course) {
            titleVH.text = course.title
            courseVH = course
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_course,
            parent,
            false
        )
        return CourseHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }
}