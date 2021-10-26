package com.skillbox.networking.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.skillbox.networking.R
import com.skillbox.networking.extensions.setNavigationResult

class DialogFragmentRating: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_ratings, null)

        val editTextRating = view.findViewById<EditText>(R.id.editTextRatings)
        builder.setView(view)
            .setTitle("Оцените фильм от 1 до 10")
            .setPositiveButton("OK"){ _, _ ->
                setNavigationResult(editTextRating.text.toString(), KEY_RATING)
            }
            .setNegativeButton("CANCEL"){dialog, _->
            }

        return builder.create()

    }
    companion object{
        const val KEY_RATING = "key_rating"
    }
}