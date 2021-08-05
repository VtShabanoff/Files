package com.skillbox.car.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.skillbox.car.R
import com.skillbox.car.`interface`.TransferringDate
import com.skillbox.car.databinding.VehicleCreateDialogBinding
import java.util.zip.Inflater

class DialogCreateVehicle: DialogFragment() {

    private val TransferringDate: TransferringDate?
        get() = parentFragment?.let { it as TransferringDate }

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
                TransferringDate?.onTransferDate(
                    textModel.text.toString(),
                    textMake.text.toString(),
                    isElectric.isChecked
                )
            }
            .setNegativeButton("CANCEL"){dialog, _->
                dialog.cancel()
            }
       return builder.create()
    }
}