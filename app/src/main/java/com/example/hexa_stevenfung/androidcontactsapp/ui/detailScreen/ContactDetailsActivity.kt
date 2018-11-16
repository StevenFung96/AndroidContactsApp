package com.example.hexa_stevenfung.androidcontactsapp.ui.detailScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.hexa_stevenfung.androidcontactsapp.R
import com.example.hexa_stevenfung.androidcontactsapp.model.Contact
import com.example.hexa_stevenfung.androidcontactsapp.util.loadUrl
import kotlinx.android.synthetic.main.activity_contact_details.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class ContactDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var selectedContact = intent.getParcelableExtra<Contact>("contact")
        tv_name.text = selectedContact.name
        tv_phone.text = selectedContact.phone

        //another usage of loadUrl extension method
        iv_profile.loadUrl(selectedContact.image ?: "")

        tv_name.onClick {
            toast(tv_name.text)
        }

        tv_phone.onClick {
            toast(tv_phone.text)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            finish()
        return true
    }
}