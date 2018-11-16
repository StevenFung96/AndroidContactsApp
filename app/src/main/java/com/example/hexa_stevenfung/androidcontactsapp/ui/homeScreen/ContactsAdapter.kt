package com.example.hexa_stevenfung.androidcontactsapp.ui.homeScreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hexa_stevenfung.androidcontactsapp.R
import com.example.hexa_stevenfung.androidcontactsapp.model.Contact
import com.example.hexa_stevenfung.androidcontactsapp.util.inflate
import com.example.hexa_stevenfung.androidcontactsapp.util.loadUrl
import kotlinx.android.synthetic.main.item_contact.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    private var items = ArrayList<Contact>()
    private lateinit var listener: OnItemSelectedListener

    /*
    What if we want to send the data and listener within the constructor? how to create a constructor here?
     */
    fun setData(items: ArrayList<Contact>?) {
        if (items != null) {
            this.items = items
            notifyDataSetChanged()
        }
    }

    fun setClickListener(listener: OnItemSelectedListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        /*The line that inflates the view and uses parent is too complex,and 99% of the time is usually the same on any adapters.
          Why not give ViewGroups the ability to inflate views?
         */

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        val view = parent.inflate(R.layout.item_contact)

        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        holder.name.text = items[position].name
        holder.phone.text = items[position].phone

        /*
        We can replace this with an extension
        */
        //Glide.with(holder.container).load(items[position].image).into(holder.profilePic)
        holder.profilePic.loadUrl(items[position].image?:"")

        holder.container.onClick {
            listener.onItemSelected(items[position])
        }
    }

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /*
        No need to call findViewById(R.id.textView) as TextView
        */

/*      val container = itemView.findViewById<CardView>(R.id.container)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val phone = itemView.findViewById<TextView>(R.id.tv_phone)
        val profilePic = itemView.findViewById<CircleImageView>(R.id.iv_profile)*/

        val container = itemView.container
        val name = itemView.tv_name
        val phone = itemView.tv_phone
        val profilePic = itemView.iv_profile

    }

    interface OnItemSelectedListener {

        fun onItemSelected(selectedContact: Contact)

    }

}