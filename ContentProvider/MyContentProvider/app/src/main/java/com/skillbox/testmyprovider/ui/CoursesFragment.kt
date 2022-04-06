package com.skillbox.testmyprovider.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.testmyprovider.R
import com.skillbox.testmyprovider.databinding.FragmentContetntBinding

class CoursesFragment : Fragment(R.layout.fragment_contetnt) {
    private val binding by viewBinding(FragmentContetntBinding::class.java)
    private val viewModel: CoursesViewModel by viewModels()
    private var courseAdapter: CoursesListAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        addCourse()
        observerUpdateCourses()
        requestCourses()
    }

    private fun setupRecyclerView() {
        with(binding.coursesRv) {
            courseAdapter = CoursesListAdapter { course ->
                findNavController()
                    .navigate(
                        R.id.action_contentFragment_to_detailedFragment,
                        bundleOf(TITLE to course.title)
                    )
            }
            adapter = courseAdapter
        }
    }

    private fun addCourse(){
        binding.buttonAddCourse.setOnClickListener {
            findNavController()
                .navigate(R.id.action_contentFragment_to_addCourseFragment)
        }
    }

    private fun observerUpdateCourses(){
        viewModel.courses.observe(viewLifecycleOwner){
            courseAdapter?.submitList(it)
        }
    }

    private fun requestCourses(){
        viewModel.getAllCourses()
    }

    companion object {
        const val TITLE = "title"
    }
}