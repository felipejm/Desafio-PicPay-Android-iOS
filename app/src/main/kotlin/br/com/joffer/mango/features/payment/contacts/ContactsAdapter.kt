package br.com.joffer.mango.features.payment.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.joffer.mango.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_contacts_item.view.*

class ContactsAdapter(val fullContacts: List<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>(), Filterable{

    private var contacts: List<Contact> = fullContacts
    var onClickListener: (Contact) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_contacts_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(contact: Contact){
            itemView.name.text = contact.name
            itemView.username.text = contact.username

            Glide.with(itemView)
                .load(contact.img)
                .apply(RequestOptions().circleCrop())
                .transition(withCrossFade())
                .into(itemView.avatar)

            itemView.setOnClickListener { onClickListener(contact) }
        }
    }

    override fun getFilter(): Filter {
        return object :Filter(){

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filtered = if(constraint.isNullOrEmpty()){
                    fullContacts
                }else{
                    val query = constraint.toString().toLowerCase()
                    fullContacts.filter {
                        it.name.toLowerCase().contains(query) or it.username.toLowerCase().contains(query)
                    }
                }

                val result = FilterResults()
                result.values = filtered
                result.count = filtered.size
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                contacts = results?.values as? List<Contact> ?: fullContacts
                notifyDataSetChanged()
            }
        }
    }
}