package Adapter


import Database.GlobalVar
import Hewan
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.example.week2_0706012110046.AddActivity
import com.example.week2_0706012110046.R
import com.example.week2_0706012110046.databinding.CardHewanBinding

class ListHewanRVAdapter(private val data: MutableList<Hewan>):
    RecyclerView.Adapter<ListHewanRVAdapter.viewHolder>() {


    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //bind
        val binding = CardHewanBinding.bind(itemView)


        fun setData(data: Hewan) {
            binding.cardNamahewan.text = data.nama
            binding.cardJenishewan.text = data.jenis
            binding.cardUsiahewan.text = data.usia

            if (!data.imageUri!!.isEmpty()) {
                binding.cardImageview.setImageURI(Uri.parse(data.imageUri))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_hewan, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(data[position])
        with(holder) {
            binding.cardDeletebutton.setOnClickListener() {
                GlobalVar.listDataHewan.removeAt(position)

                notifyItemRemoved(position)
                notifyItemChanged(position, itemCount)
                Toast.makeText(it.context, "Animal succesfully deleted", Toast.LENGTH_SHORT).show()
            }
            binding.cardEditbutton.setOnClickListener() {
                val myIntent = Intent(it.context, AddActivity::class.java)
                myIntent.putExtra("position", position)

                it.context.startActivity(myIntent)

            }
            holder.binding.cardInteractbutton.setOnClickListener {
                if (GlobalVar.filterjenishewan.isEmpty() ) {
                    if (GlobalVar.listDataHewan.get(position) is Ayam ) {
                        Toast.makeText(
                            it.context,
                            GlobalVar.listDataHewan.get(position).suaraHewan(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                   else if (GlobalVar.listDataHewan.get(position) is Sapi ) {
                        Toast.makeText(
                            it.context,
                            GlobalVar.listDataHewan.get(position).suaraHewan(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                   else if (GlobalVar.listDataHewan.get(position) is Kambing ) {
                        Toast.makeText(
                            it.context,
                            GlobalVar.listDataHewan.get(position).suaraHewan(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    if (GlobalVar.filterjenishewan[position] is Ayam) {
                        Toast.makeText(
                            it.context,
                            GlobalVar.filterjenishewan[position].suaraHewan(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                   else if (GlobalVar.filterjenishewan[position] is Sapi) {
                        Toast.makeText(
                            it.context,
                            GlobalVar.filterjenishewan.get(position).suaraHewan(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                   else if (GlobalVar.filterjenishewan[position] is Kambing) {
                        Toast.makeText(
                            it.context,
                            GlobalVar.filterjenishewan[position].suaraHewan(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


                holder.binding.cardFeedbutton.setOnClickListener {
                    if (GlobalVar.filterjenishewan.isEmpty()) {
                        if (GlobalVar.listDataHewan[position] is Ayam ) {
                            Toast.makeText(
                                it.context,
                                GlobalVar.listDataHewan[position].feedHewan(jenis0 = String()),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (GlobalVar.listDataHewan[position] is Sapi ) {
                            Toast.makeText(
                                it.context,
                                GlobalVar.listDataHewan[position].feedHewan(jenis = 0),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (GlobalVar.listDataHewan[position] is Kambing ) {
                            Toast.makeText(
                                it.context,
                                GlobalVar.listDataHewan[position].feedHewan(jenis = 0),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        if (GlobalVar.filterjenishewan[position] is Ayam) {
                            Toast.makeText(
                                it.context,
                                GlobalVar.filterjenishewan[position].feedHewan(jenis0 = String()),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (GlobalVar.filterjenishewan[position] is Sapi) {
                            Toast.makeText(
                                it.context,
                                GlobalVar.filterjenishewan[position].feedHewan(jenis = 0),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (GlobalVar.filterjenishewan[position] is Kambing) {
                            Toast.makeText(
                                it.context,
                                GlobalVar.filterjenishewan[position].feedHewan(jenis = 0),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }


        }


    }

    override fun getItemCount(): Int {
        return data.size
    }
}
