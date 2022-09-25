package Database

import Hewan

class GlobalVar {

    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
        val listDataHewan:MutableList<Hewan> = ArrayList()
        val filterjenishewan:MutableList<Hewan> = ArrayList()
    }
}