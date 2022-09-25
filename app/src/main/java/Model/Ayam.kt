package Model

import Hewan


class Ayam(nama:String?,jenis:String?,usia:String?):Hewan(nama,jenis,usia) {

override fun suaraHewan():String{

    return "pock pock pock pock"
}

    override fun feedHewan(jenis0: String?):String{

        return "kamu memberi makan hewan dengan biji bijian"
    }
}