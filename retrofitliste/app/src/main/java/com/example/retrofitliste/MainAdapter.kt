package com.example.retrofitliste


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitliste.R

import kotlinx.android.synthetic.main.todo_row.view.*

/**
 * Created by brianvoong on 12/18/17.
 */

class MainAdapter(val article :List<TodoItem>): RecyclerView.Adapter<CustomViewHolder>() {



    // numberOfItems
    override fun getItemCount(): Int {
        return article.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.todo_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val item = article.get(position)
        holder?.view?.textView_video_title?.text = "TACHE :"+item.title
        holder?.view?.idtache?.text = "Numero :"+item.id.toString()
        if (item.completed){holder?.view?.complete?.text="DONE"
            holder?.view?.complete?.setTextColor(R.color.colorPrimaryDark)}
        else{holder?.view?.complete?.text="TO DO"
            holder?.view?.complete?.setTextColor(R.color.yellow)}
    }



}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}

