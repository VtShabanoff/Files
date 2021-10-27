package com.skillbox.networking.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.skillbox.networking.R
import com.skillbox.networking.ViewModelMovieList
import com.skillbox.networking.extensions.setNavigationResult

class DialogFragmentRating: DialogFragment() {
    private val viewModel by activityViewModels<ViewModelMovieList>()
    private val args by navArgs<DialogFragmentRatingArgs>()// аргументы из фрагмента

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_ratings, null)

        val editTextRating = view.findViewById<EditText>(R.id.editTextRatings)

        builder.setView(view)
            .setTitle("Оцените фильм от 1 до 10")
            .setPositiveButton("OK"){ _, _ ->
                viewModel.rateMovie(args.id,editTextRating.text.toString())
            }
            .setNegativeButton("CANCEL"){dialog, _->
                dialog.dismiss()
            }

        return builder.create()

    }
}