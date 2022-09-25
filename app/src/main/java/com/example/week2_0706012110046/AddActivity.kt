package com.example.week2_0706012110046
import Database.GlobalVar.Companion.listDataHewan
import Database.GlobalVar
import Hewan
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isEmpty
import com.example.week2_0706012110046.databinding.ActivityAddBinding


class AddActivity : AppCompatActivity() {
    var siu:String?=null
    private lateinit var hewan: Hewan
    private var position = -1
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GetIntent()
        listener()

        supportActionBar?.hide()
    }
    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== Activity.RESULT_OK){
            val uri = it.data?.data
            binding.addImage.setImageURI(uri)
            if(uri!=null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    baseContext.getContentResolver().takePersistableUriPermission(

                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                siu=uri.toString()

            }
        }
    }

    private fun listener() {
        binding.addBackbutton.setOnClickListener() {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
        binding.addImage.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type= "image/*"
            GetResult.launch(myIntent)
        }

        binding.buttonSave.setOnClickListener{
            var nama = binding.addTextinputlayoutnama.editText?.text.toString().trim()

            var usia = binding.addTextinputlayoutusia.editText?.text.toString().trim()
            var jenis =
                if (binding.addradiogroupJenishewan.checkedRadioButtonId == binding.addradiobuttonAyam.id){
                    "Ayam"
                }else if(binding.addradiogroupJenishewan.checkedRadioButtonId == binding.addRadiobuttonsapi.id){
                    "Sapi"
                }else{
                    "Kambing"
                }
            if (jenis == "Ayam"){
                    hewan = Ayam(nama, jenis, usia)
            }
            else if(jenis == "Sapi"){
                hewan = Sapi(nama, jenis, usia)
            }
            else{
                hewan = Kambing(nama, jenis, usia)
            }


            checker()
        }
    }
    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position!=-1) {
            binding.addTambahhewan.setText("Edit Hewan")

            val hewan = listDataHewan[position]
            Display(hewan)
        }
    }
    private fun Display(hewan: Hewan?) {
        binding.addTextinputlayoutnama.editText!!.setText(hewan!!.nama)

        var jenis= hewan!!.jenis
        if(jenis=="Ayam"){
            binding.addradiobuttonAyam.isChecked=true
        }
        else if(jenis=="Sapi"){
            binding.addRadiobuttonsapi.isChecked=true
        }
        else{
            binding.addRadiobuttonkambing.isChecked=true
        }

        binding.addTextinputlayoutusia.editText!!.setText(hewan!!.usia)
        binding.addImage.setImageURI(Uri.parse(hewan.imageUri))
    }
    private fun checker() {
        var isCompleted: Boolean = true


        if (binding.addTextinputlayoutnama.isEmpty()) {
            binding.addTextinputlayoutnama.error ="Tolong isi kolom nama"
            isCompleted = false
        } else {
            binding.addTextinputlayoutnama.error = ""
        }


        if(binding.addradiogroupJenishewan.checkedRadioButtonId ==-1){
            Toast.makeText(this, "Tolong Pilih  kolom Hewan", Toast.LENGTH_SHORT).show()
            isCompleted=false
        }



        if (binding.addTextinputlayoutusia.isEmpty()) {
            binding.addTextinputlayoutusia.error = "Tolong isi kolom usia"
            isCompleted = false
        } else {
            binding.addTextinputlayoutusia.error = ""
        }


        if(isCompleted){
            if(position==-1){
                hewan.imageUri=siu.toString()
                listDataHewan.add(hewan)
                Toast.makeText(this,"Animal succesfully added", Toast.LENGTH_SHORT).show()
                val myIntent=Intent(this,MainActivity::class.java)
                startActivity(myIntent)
            }
            else{
                if(siu==GlobalVar.listDataHewan[position].imageUri){
                    hewan.imageUri=GlobalVar.listDataHewan[position].imageUri
                }
                else if(siu==""){
                    hewan.imageUri=GlobalVar.listDataHewan[position].imageUri
                }
                else{


                    hewan.imageUri=siu.toString()
                    GlobalVar.listDataHewan[position]=hewan
                    Toast.makeText(this,"Animal succesfully edited", Toast.LENGTH_SHORT).show()
                    val myIntent=Intent(this,MainActivity::class.java)
                    startActivity(myIntent)
                }
            }
            finish()
        }


    }
}