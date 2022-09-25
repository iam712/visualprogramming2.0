package com.example.week2_0706012110046


import Adapter.ListHewanRVAdapter
import Database.GlobalVar
import Database.GlobalVar.Companion.filterjenishewan

import Database.GlobalVar.Companion.listDataHewan

import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager

import com.example.week2_0706012110046.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val adapter = ListHewanRVAdapter(listDataHewan)
    private var adapter2 = ListHewanRVAdapter(filterjenishewan)

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRecyclerView()
        listener()
        CheckPermissions()
    }
    private fun listener(){
        binding.mainFab.setOnClickListener {
            val myIntent = Intent(it.context, AddActivity::class.java)
            it.context.startActivity(myIntent)
        }
        binding.mainButtonfilterayam.setOnClickListener {
            filterjenishewan.clear()
            for (item in listDataHewan){
                if (item is Ayam){
                    filterjenishewan.add(item)
                }
            }
            binding.mainRv.adapter = adapter2
        }
        binding.mainButtonfiltersapi.setOnClickListener {
            filterjenishewan.clear()
            for (item in listDataHewan){
                if (item is Sapi){
                    filterjenishewan.add(item)
                }
            }
            binding.mainRv.adapter = adapter2
        }
        binding.mainButtonfilterkambing.setOnClickListener {
            filterjenishewan.clear()
            for (item in listDataHewan){
                if (item is Kambing){
                    filterjenishewan.add(item)
                }
            }
            binding.mainRv.adapter = adapter2
        }
        binding.mainButtonreset.setOnClickListener {
            binding.mainRv.adapter=adapter
        }
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(baseContext, 1)
        binding.mainRv.layoutManager= layoutManager //Set layout
        binding.mainRv.adapter=adapter //Set adapter

    }
    private fun CheckPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }






}