import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imoappui.R
import com.example.imoappui.adapter.ContactAdapter
import com.example.imoappui.model.ContactInfo

class ContactsFragment(val contacts: ArrayList<ContactInfo>) : Fragment() {

    private lateinit var recyclerContacts: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chats, container, false)

        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        recyclerContacts = view.findViewById(R.id.rv_chats)
        recyclerContacts.layoutManager = GridLayoutManager(context, 1)

        refreshAdapter()
    }

    fun refreshAdapter() {
        val contactAdapter = ContactAdapter(contacts)
        Log.d("RRR", "Size: " + contacts.size)
        recyclerContacts.adapter = contactAdapter
    }

}