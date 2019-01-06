package com.calvin.skripsi.kanabisa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.calvin.skripsi.kanabisa.model.Evaluasi

open class EvaluasiListAdapter(data: ArrayList<Evaluasi>, context: Context): BaseAdapter() {

    val dbh = DBHelper(context)
    val mInflater: LayoutInflater = LayoutInflater.from(context)
    var locdat = data

    override fun getCount(): Int {
        return locdat.size
    }

    override fun getItem(position: Int): Any {
        return locdat[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_histori, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.eval.text = "Evaluasi " + locdat.get(index = position).id
        vh.tang.text = locdat.get(index = position).tanggal
        vh.has.text = "Hasil: " + locdat.get(index = position).benar + "/" + locdat.get(index = position).banyak
        return view
    }

    private class ListRowHolder(row: View?) {
        val eval: TextView
        val tang: TextView
        val has: TextView

        init {
            this.eval = row?.findViewById(R.id.evaluasi) as TextView
            this.tang = row?.findViewById(R.id.tanggal) as TextView
            this.has = row?.findViewById(R.id.hasil) as TextView
        }
    }
}