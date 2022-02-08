package com.example.imoappui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imoappui.R
import com.example.imoappui.model.Chat
import com.google.android.material.imageview.ShapeableImageView

class ChatAdapter(var context: Context, var items: ArrayList<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat_view, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = items[position]

        if (holder is MessageViewHolder) {
            val iv_profile = holder.iv_profile
            val tv_fullname = holder.tv_fullname
            val tv_count = holder.tv_count

            iv_profile.setImageResource(chat.profile)
            tv_fullname.text = chat.fullname
            if (chat.count < 9)
                tv_count.text = chat.count.toString()
            else tv_count.text = "9+"

            if (chat.count > 0) {
                tv_count.visibility = View.VISIBLE
            } else {
                tv_count.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = items.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv_profile: ShapeableImageView
        val tv_fullname: TextView
        val tv_count: TextView

        init {
            iv_profile = itemView.findViewById(R.id.iv_profile)
            tv_fullname = itemView.findViewById(R.id.tv_fullname)
            tv_count = itemView.findViewById<TextView>(R.id.tv_count)
        }
    }
}