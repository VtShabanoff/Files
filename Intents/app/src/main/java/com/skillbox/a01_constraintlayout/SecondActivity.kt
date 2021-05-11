package com.skillbox.a01_constraintlayout

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        buttonPhone.setOnClickListener {
            dialPhoneNumber()
        }
    }
    private fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
    private fun dialPhoneNumber(){
        val isNumberPhoneCorrect = Patterns.PHONE.matcher(editTextPhone.text.toString()).matches()
        val intentDial = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${editTextPhone.text}"))
        if (intentDial.resolveActivity(packageManager) != null){
            if (isNumberPhoneCorrect) {
                startActivityForResult(intentDial, REQUEST_CODE_SECOND_ACTIVITY)
            } else{
                toast("Введите корректный номер телефона")
            }
        }
    }

    companion object {
        const val REQUEST_CODE_SECOND_ACTIVITY = 1
    }

}