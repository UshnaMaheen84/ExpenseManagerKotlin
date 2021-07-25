package com.example.expensemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.models.UserModel

class UserAdapter(val model_list: List<UserModel>,val context: Context) :RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false);
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item= model_list[position]
        holder.name.text= item.name
    }

    override fun getItemCount()= model_list.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.emerName)
        val card: CardView = itemView.findViewById(R.id.cardview)
    }


}