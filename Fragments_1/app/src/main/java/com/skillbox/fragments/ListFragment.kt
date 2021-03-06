package com.skillbox.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment

class ListFragment: Fragment(R.layout.fragment_list){

    private val itemSelectListener: ItemSelectListener?
        get() = parentFragment as ItemSelectListener?

    override fun onViewCreated(view1: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view1, savedInstanceState)

        view.let { it as ViewGroup }
            .children
            .mapNotNull { it as TextView }
            .forEach { textView -> textView.setOnClickListener {
                onTextViewClick()
            } }
    }

    private fun onTextViewClick(){
        itemSelectListener?.onItemSelected()
    }


}

