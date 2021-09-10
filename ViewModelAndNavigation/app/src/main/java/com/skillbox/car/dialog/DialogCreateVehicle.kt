package com.skillbox.car.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.skillbox.car.R

class DialogCreateVehicle: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.vehicle_create_dialog, null)

        val textModel = view.findViewById<TextView>(R.id.modelEditText)
        val textMake = view.findViewById<TextView>(R.id.makeEditText)
        val isElectric = view.findViewById<CheckBox>(R.id.checkBoxType)

        builder.setView(view)
            .setTitle("Введите данные")
            .setPositiveButton("OK"){ _, _ ->
                val actions = DialogCreateVehicleDirections
                    .actionDialogCreateVehicleToVehicleListFragment(
                        textModel.text.toString(),
                        textMake.text.toString(),
                        isElectric.isChecked
                    )
                findNavController().navigate(actions)

            }
            .setNegativeButton("CANCEL"){dialog, _->
                dialog.cancel()
            }
            .show()
       return builder.create()
    }
}