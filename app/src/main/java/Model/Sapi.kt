package Model

import Hewan


class Sapi(nama:String?,jenis:String?,usia:String?):Hewan(nama,jenis,usia) {
    override fun suaraHewan():String{

        return "moooo"

    }

    override fun feedHewan(jenis: Int):String {

        return "kamu memberi makan hewan dengan rerumputan"
    }
}