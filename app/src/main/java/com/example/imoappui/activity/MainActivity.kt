package com.example.imoappui.activity

import ContactsFragment
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.imoappui.R
import com.example.imoappui.adapter.ChatAdapter
import com.example.imoappui.adapter.ViewPagerAdapter
import com.example.imoappui.fragments.ChatsFragment
import com.example.imoappui.model.Chat
import com.example.imoappui.model.ContactInfo
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private lateinit var contacts: ArrayList<ContactInfo>
    private lateinit var context: Context
    private lateinit var phoneNumbers: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        checkPermissions()
    }

    private fun initViews(){
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewPager)

        contacts = ArrayList()
        phoneNumbers = ArrayList()

        refreshAdapter()

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun refreshAdapter(){
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.add(ChatsFragment(), "chats")
        viewPagerAdapter.add(ContactsFragment(contacts), "contacts")

        viewPager.adapter = viewPagerAdapter
    }

    fun checkPermissions(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity
                , arrayOf(android.Manifest.permission.READ_CONTACTS)
                , 100)
        } else{
            getContactList()
        }
    }

    @SuppressLint("Range")
    fun getContactList(){
        val uri = ContactsContract.Contacts.CONTENT_URI
        val sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC"
        val cursor = contentResolver.query(uri,null,null,null,sort)

        if (cursor!!.count > 0){
            while (cursor.moveToNext()){
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                val selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?"

                val phoneCursor = contentResolver.query(uriPhone,null,selection, arrayOf(id), null)

                if (phoneCursor!!.moveToNext()){
                    val number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    contacts.add(ContactInfo(R.drawable.ic_contacts_logo, name, number))
                    phoneNumbers.add(number)
                    phoneCursor.close()
                }
            }

            cursor.close()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Log.d("RRR", "onRequestPermissionsResult: ")
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //refreshAdapter()
            getContactList()
        }

        else{
            Toast.makeText(this, "PD", Toast.LENGTH_SHORT).show()
            checkPermissions()
        }
    }
}