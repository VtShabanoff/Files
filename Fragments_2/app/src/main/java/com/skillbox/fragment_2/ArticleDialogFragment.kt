package com.skillbox.fragment_2

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ArticleDialogFragment(): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val selectedItems = ArrayList<Int>() // Where we track the selected items
            val sortedList = ArrayList<ArticlesList>()
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle("choice group")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(, null)
                { _, which, isChecked ->
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(which)
                        } else if (selectedItems.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(which))
                        }
                    }
                // Set the action buttons
                .setPositiveButton("OK") { dialog, id ->

                    }
                .setNegativeButton("CANCEL",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}