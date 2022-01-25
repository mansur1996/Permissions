package com.example.smssender

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val CODE = 1001

class MainActivity : AppCompatActivity() {


    private lateinit var phoneNumber : EditText
    private lateinit var smsText : EditText
    private lateinit var sendBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        requestPermissions()
    }
    private fun initViews(){
        phoneNumber = findViewById(R.id.et_phoneNumber)
        smsText = findViewById(R.id.et_textSMS)
        sendBtn = findViewById(R.id.btn_send)


    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationPermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasSmsPermission() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions(){
        var permissionsToRequest = mutableListOf<String>()
        if (!hasCameraPermission()){
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }
        if (!hasLocationPermission()){
            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!hasSmsPermission()){
            permissionsToRequest.add(Manifest.permission.RECEIVE_SMS)
        }

        if (permissionsToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionsToRequest.toTypedArray(), CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODE && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "${permissions[i]} granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}