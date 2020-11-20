package com.udacity.shoestore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeAdapter(private val context: Context, private val dataSource: Array<Shoe?> ): BaseAdapter(){
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }
    //2
    override fun getItem(position: Int): Shoe? {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.card_item, parent, false)
        val nameText= rowView.findViewById(R.id.nameText) as TextView
        val companyText= rowView.findViewById(R.id.companyText) as TextView
        val sizeText = rowView.findViewById(R.id.sizeText) as TextView
        val descriptionText = rowView.findViewById(R.id.descriptionText) as TextView

        val shoe = getItem(position) as Shoe
        nameText.text = shoe.name
        companyText.text = shoe.company
        descriptionText.text = shoe.description
        sizeText.text = (shoe.size).toString()
        return rowView
    }

}