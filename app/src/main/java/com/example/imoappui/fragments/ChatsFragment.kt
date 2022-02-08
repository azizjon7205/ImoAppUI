package com.example.imoappui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imoappui.R
import com.example.imoappui.adapter.ChatAdapter
import com.example.imoappui.model.Chat

class ChatsFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chats, container, false)
        initViews(itemView = view)
        return view
    }
    private fun initViews(itemView: View){
        recyclerView = itemView.findViewById(R.id.rv_chats)
        recyclerView.layoutManager = GridLayoutManager(context, 1)

        refreshAdapter(getAllChats())
    }

    fun refreshAdapter(chats: ArrayList<Chat>){
        val adapter = ChatAdapter(requireContext(), chats)
        recyclerView.adapter = adapter
    }

    fun getAllChats(): ArrayList<Chat>{
        val chats = ArrayList<Chat>()

        chats.add(Chat(R.drawable.im_user_1, "Sheronov Azizjon", 1))
        chats.add(Chat(R.drawable.im_user_2, "Sheronov Muxtor", 5))
        chats.add(Chat(R.drawable.im_user_3, "Hamrakulov Botirbek", 0))
        chats.add(Chat(R.drawable.im_user_2, "Ergashev Jasurbek", 10))
        chats.add(Chat(R.drawable.im_user_3, "Jurayev Sherbek", 3))
        chats.add(Chat(R.drawable.im_user_2, "Turakulov Umidjon", 2))
        chats.add(Chat(R.drawable.im_user_1, "Sheronov Azizjon", 7))
        chats.add(Chat(R.drawable.im_user_3, "Ergashev Jasurbek", 12))
        chats.add(Chat(R.drawable.im_user_2, "Sheronov Muxtor", 0))
        chats.add(Chat(R.drawable.im_user_3, "Jurayev Sherbek", 1))

        return chats
    }
}