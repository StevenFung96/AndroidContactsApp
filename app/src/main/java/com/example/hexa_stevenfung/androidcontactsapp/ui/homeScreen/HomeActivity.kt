package com.example.hexa_stevenfung.androidcontactsapp.ui.homeScreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.hexa_stevenfung.androidcontactsapp.R
import com.example.hexa_stevenfung.androidcontactsapp.model.Contact
import com.example.hexa_stevenfung.androidcontactsapp.ui.detailScreen.ContactDetailsActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONArray
import java.io.IOException

class HomeActivity : AppCompatActivity(), ContactsAdapter.OnItemSelectedListener {

    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        adapter = ContactsAdapter()
        list_report.adapter = adapter
        adapter.setClickListener(this@HomeActivity)

        btn_get_data.setOnClickListener {

            progress_loading.visibility = View.VISIBLE
            list_report.visibility = View.GONE
            tv_error.visibility = View.GONE

            getContactsFromServer()
        }
    }

    /**
     * this method read some mocked data from a local json file and return to us a string stream
     */
    private fun getMockedContacts(): String? {
        try {
            val inputStream = resources.openRawResource(R.raw.dummy_data)

            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            return String(bytes)
        } catch (e: IOException) {

        }
        return null
    }

    /**
     * this method fetch the data from the server and update the UI
     */
    private fun getContactsFromServer() {
        var contactsArray = JSONArray(getMockedContacts())

        var contactItems = ArrayList<Contact>()
        for (i in 0..(contactsArray.length() - 1)) {
            val item = contactsArray.getJSONObject(i)

            var name = item.getString("name")
            var phone = item.getString("phone")
            var imageUrl = item.getString("image")
            contactItems.add(Contact(name, phone, imageUrl))
        }

        list_report.visibility = View.VISIBLE
        progress_loading.visibility = View.GONE
        tv_error.visibility = View.GONE

        adapter.setData(contactItems)

    }

    override fun onItemSelected(selectedContact: Contact) {
        var intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra("contact", selectedContact)
        startActivity(intent)
    }

}