package Model

import Hewan
import android.widget.Toast

class Kambing(nama:String?,jenis:String?,usia:String?):Hewan(nama,jenis,usia) {
    override fun suaraHewan():String{

        return "blehhh"
    }

    override fun feedHewan(jenis: Int):String {

        return "kamu memberi makan hewan dengan rerumputan"
    }
}