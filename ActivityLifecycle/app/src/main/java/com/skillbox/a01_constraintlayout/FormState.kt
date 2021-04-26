package com.skillbox.a01_constraintlayout

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FormState(var valid: Boolean, val message: String):Parcelable {

    fun displayMessageErrorEmailAddress(): FormState{
        return copy(message = "Некорректная форма email адреса")
    }

}
