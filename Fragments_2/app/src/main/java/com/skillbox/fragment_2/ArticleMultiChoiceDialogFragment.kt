package com.skillbox.fragment_2

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ArticleMultiChoiceDialogFragment: DialogFragment() {
    private val arrayArticleSectionNames = arrayOf(
        ArticleSection.CAT.name,
        ArticleSection.DOG.name,
        ArticleSection.BIRD.name,
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>() // Where we track the selected items
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle("Выбири группу")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(arrayArticleSectionNames, null) { dialog, which, isChecked ->
                    if (isChecked) {
                        // If the user checked the item, add it to the selected items
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        // Else, if the item is already in the array, remove it
                        selectedItems.remove(Integer.valueOf(which))
                    }
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}