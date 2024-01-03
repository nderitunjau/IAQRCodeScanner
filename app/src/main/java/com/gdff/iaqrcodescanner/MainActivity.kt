package com.gdff.iaqrcodescanner

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.gdff.iaqrcodescanner.databinding.ActivityMainBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {
    private val requestPermissionLauncher=
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted:Boolean ->
            if (isGranted){
                popCamera()
            }else{

            }
        }
    private val scanLauncher =
        registerForActivityResult(ScanContract()){result:ScanIntentResult ->run{
            if (result.contents == null){
                    Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
            }else{
                    setResult(result.contents)
            }
        }

        }
        //This shows all the data in one text.
    private lateinit var binding:ActivityMainBinding
    private fun setResult(string: String){
        binding.textResult.text=string
    }
    //This shows code parsed in small bits
    
     private fun setResult(string: String){
        val parts = string.split("~") // Split the string by ~
        if (parts.size > 2) {
            val specificPart = parts[2] // Accessing the third element (index 2)
            binding.seg3.text = specificPart // Display the specific part
        } else {
            // Handle the case where there might not be enough parts
            binding.seg3.text = "No valid data found"
           // binding.seg1.text = parts[0]
           // binding.seg2.text = parts[1]
           // binding.seg3.text = parts[2]
        }
        //binding.textResult.text=string
    }

    private fun popCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan your QR Code")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(false)
        scanLauncher.launch(options)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()

    }

    private fun initViews() {
        binding.fab.setOnClickListener{
            checkPermissionCamera(this)
        }
    }

    private fun checkPermissionCamera(context: Context) {
        if(ContextCompat.checkSelfPermission(context,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                popCamera()
        }
        else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
            Toast.makeText(context,"Camera permission required",Toast.LENGTH_SHORT).show()
        }
        else{
                requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
