package com.example.imoappui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imoappui.R
import com.example.imoappui.model.ContactInfo

class ContactAdapter(var contacts: ArrayList<ContactInfo>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_contacts, null)
        return ContactsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = contacts[position]

        if (holder is ContactsViewHolder){
            val tv_name = (holder as ContactsViewHolder).tv_name
            val tv_phone_number = holder.tv_phone_number
            val iv_profile = holder.iv_profile
            val lay_contact = holder.lay_contact

            tv_name.text = contact.name
            tv_phone_number.text = contact.phoneNumber
            iv_profile.setImageResource(contact.profile)

        }
    }

    override fun getItemCount(): Int = contacts.size

    private class ContactsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val lay_contact: LinearLayout
        var tv_name: TextView
        lateinit var tv_phone_number: TextView
        lateinit var iv_profile: ImageView
        init {
            lay_contact = itemView.findViewById(R.id.lay_contact)
            tv_name = itemView.findViewById(R.id.tv_contact_name)
            tv_phone_number = itemView.findViewById(R.id.tv_phone_number)
            iv_profile = itemView.findViewById(R.id.iv_profile)
        }
    }

}