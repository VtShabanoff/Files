package com.skillbox.fragment_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ArticleDialogFragment: DialogFragment() {
    private var selectedItems = ArrayList<Int>()
    private val itemMultiChoiceListener: ItemMultiChoiceListener
        get() = activity as ItemMultiChoiceListener

    private val articleNames = arrayOf(
        ArticleSection.CAT.name,
        ArticleSection.DOG.name,
        ArticleSection.BIRD.name,
    )
    var arrayCheckItems = booleanArrayOf(false, false, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arrayCheckItems = requireArguments().getBooleanArray(KEY_CHECKS)!!
        selectedItems = requireArguments().getIntegerArrayList(KEY_SELECTED_LIST) as ArrayList<Int>
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            builder.setTitle("choice group")
                .setMultiChoiceItems(articleNames, arrayCheckItems)
                { _, which, isChecked ->
                        if (isChecked) {
                            selectedItems.add(which)
                            arrayCheckItems[which] = true
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(which)
                            arrayCheckItems[which] = false
                        }
                    }
                .setPositiveButton("OK") { _, _ ->
                    if (selectedItems.size > 0) {
                        itemMultiChoiceListener.onItemMultiChoice(selectedItems, arrayCheckItems)
                    }
                    }
                .setNegativeButton("CANCEL"){ dialog, _ ->
                        dialog.cancel()
                    }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object{
        private const val KEY_CHECKS = "key_checks"
        private const val KEY_SELECTED_LIST = "key_selected_list"
        fun newInstance(selectedList: ArrayList<Int>, arrayChecks: BooleanArray): ArticleDialogFragment{
            return ArticleDialogFragment().withArguments {
                putIntegerArrayList(KEY_SELECTED_LIST, selectedList)
                putBooleanArray(KEY_CHECKS, arrayChecks)
            }
        }
    }

}