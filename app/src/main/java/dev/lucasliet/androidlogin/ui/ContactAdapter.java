package dev.lucasliet.androidlogin.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.lucasliet.androidlogin.R;
import dev.lucasliet.androidlogin.model.Contact;
import dev.lucasliet.androidlogin.util.ImageUtil;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
    private List<Contact> results = new ArrayList<>();
    private static ItemClickListener itemClickListener;
    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = results.get(position);
        holder.textViewNome.setText(contact.getName());
        holder.textViewEmail.setText(contact.getEmail());
        holder.textViewTelefone.setText(contact.getPhone());
        holder.contactPhotoCard.setImageBitmap(ImageUtil.decode(contact.getImage()));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public void setResults(List<Contact> results) {
        this.results = results;
        notifyDataSetChanged();
    }
    class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewNome;
        private TextView textViewEmail;
        private TextView textViewTelefone;
        private ImageView contactPhotoCard;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewContactName);
            textViewEmail = itemView.findViewById(R.id.textViewContactEmail);
            textViewTelefone = itemView.findViewById(R.id.textViewContactPhone);
            contactPhotoCard = itemView.findViewById(R.id.contactPhotoCard);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition(), results.get(getAdapterPosition()));
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(int position, Contact contact);
    }
}