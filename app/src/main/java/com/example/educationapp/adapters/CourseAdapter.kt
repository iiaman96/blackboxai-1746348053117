package com.example.educationapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.educationapp.databinding.ItemCourseBinding
import com.example.educationapp.models.Course

class CourseAdapter(
    private var courses: List<Course>,
    private val onCourseClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.apply {
                tvTitle.text = course.title
                tvDescription.text = course.description
                tvInstructor.text = "Instructor: ${course.instructor}"
                tvDuration.text = course.duration

                // Load thumbnail using Glide
                Glide.with(itemView.context)
                    .load(course.thumbnailUrl)
                    .centerCrop()
                    .into(ivThumbnail)

                // Set click listener
                root.setOnClickListener {
                    onCourseClick(course)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size

    fun updateCourses(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }
}
