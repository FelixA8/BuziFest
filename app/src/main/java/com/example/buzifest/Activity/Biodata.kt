package com.example.buzifest.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.buzifest.R

class Biodata : AppCompatActivity() {

    private lateinit var biodata_firstName: EditText
    private lateinit var biodata_lastName: EditText
    private lateinit var biodata_address: EditText
    private lateinit var biodata_idCardNumber:EditText
    private lateinit var biodata_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata)

        biodata_firstName = findViewById(R.id.biodata_etfirstName)
        biodata_lastName = findViewById(R.id.biodata_etlastName)
        biodata_address = findViewById(R.id.biodata_etaddress)
        biodata_idCardNumber = findViewById(R.id.biodata_etidCardNumber)
        biodata_button = findViewById(R.id.biodata_btnRegister)
    }
}