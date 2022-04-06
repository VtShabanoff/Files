package com.skillbox.testmyprovider.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.testmyprovider.R
import com.skillbox.testmyprovider.databinding.FragmentDetailedBinding

class DetailedFragment : Fragment(R.layout.fragment_detailed) {
    private val binding by viewBinding(FragmentDetailedBinding::class.java)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleArgs =
            requireArguments().getString(CoursesFragment.TITLE) ?: error("not argument title")
        binding.titleTV.text = titleArgs
    }
}