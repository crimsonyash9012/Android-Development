package com.example.contactmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

// recycler view can display large number of items (new view for each item)

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {
    private ArrayList<Contacts> contacts;

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        // inform the associated recycler view that
        // underlying dataset has changed and recycler view should
        // refresh its view to reflect these changes
        notifyDataSetChanged();
    }

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    // parent - parent view that view holders layout will be attached
    // viewType - type of view
                    // useful when having multiple types of views
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creating new view holder for items in recylerView

        // for inflating a layout and creating a binding object
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false // whether to attach inflated layout to parent view group immediately
        );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // called by recycler view when it needs to display or update an item
        // at specific position in list or grid
        Contacts currentContact = contacts.get(position);
        holder.contactListItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        // number of items recycler view will display
        if(contacts!=null) return contacts.size();
        return 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(ContactListItemBinding contactListItemBinding) {
            // used to obtain root view of layout associated with data binding instance
            // provides access to top level view in XML file
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }


}
