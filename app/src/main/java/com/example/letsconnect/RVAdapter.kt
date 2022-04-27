package com.example.letsconnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RVAdapter( var list:List<FriendsItems>,val vaccineItemClickInterface:friendItemClickInterface, val friendsItemDeleteClickInterface:FriendsItemDeleteClickInterface):
    RecyclerView.Adapter<RVAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val frndName: TextView = itemView.findViewById(R.id.prsnName)
        val mobNum: TextView = itemView.findViewById(R.id.vccnName)
        val note: TextView = itemView.findViewById(R.id.adhrNum)
        val emailId: TextView = itemView.findViewById(R.id.dose)
        val dlt: ImageView = itemView.findViewById(R.id.dlt)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.frndName.text = list.get(position).friendName
        holder.mobNum.text = list.get(position).mobileNumber
        holder.note.text = list.get(position).note.toString()
        holder.emailId.text = list.get(position).emailId.toString()
        holder.dlt.setOnClickListener {
            friendsItemDeleteClickInterface.onItemClickDelete(list.get(position))
        }
        holder.itemView.setOnClickListener {
            //friendItemClickInterface.OnFriendItemClick(list.get(position))


        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface FriendsItemDeleteClickInterface {

        fun onItemClickDelete(vaccineItems: FriendsItems)
    }

    interface friendItemClickInterface {
        fun OnFriendItemClick(friendsItems: FriendsItems)
    }
}