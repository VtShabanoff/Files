package com.skillbox.car.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.skillbox.car.R
import com.skillbox.car.VehicleListViewModel
import com.skillbox.car.setNavigationResult
import com.skillbox.car.withArguments

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

                setNavigationResult(textModel.text.toString(), KEY_ARGS_MODEL_CAR)
                setNavigationResult(textMake.text.toString(), KEY_ARGS_MAKE_CAR)
                setNavigationResult(isElectric.isChecked, KEY_ARGS_IS_ELECTRIC_CAR)

            }
            .setNegativeButton("CANCEL"){dialog, _->
            }
       return builder.create()
    }
    companion object{
        const val KEY_ARGS_MODEL_CAR = "key_args_model_car"
        const val KEY_ARGS_MAKE_CAR = "key_args_make_car"
        const val KEY_ARGS_IS_ELECTRIC_CAR = "key_args_is_electric_car"
    }
}