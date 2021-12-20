package com.example.mobapps_kennylib;

import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<Buku> bukusList;
    List<Buku> bukusListAll;
    private OnItemClickListener mListener;

    public RecyclerAdapter(List<Buku> bukusList) {
        this.bukusList = bukusList;
        bukusListAll = new ArrayList<>();
        bukusListAll.addAll(bukusList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_buku, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.judul.setText(bukusList.get(position).getJudul());
        holder.isbn.setText(bukusList.get(position).getIsbn());
    }

    @Override
    public int getItemCount() {
        return bukusList.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Buku> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(bukusListAll);
            } else {
                for (Buku buku: bukusListAll) {
                    if (buku.getIsbn().contains(charSequence.toString().toLowerCase()) ||
                            buku.getJudul().contains(charSequence.toString().toLowerCase()) ||
                            buku.getPengarang().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(buku);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bukusList.clear();
            bukusList.addAll((Collection<? extends Buku>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        ImageView imageView;
        TextView judul, isbn;
        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView, final  OnItemClickListener listener) {
            super(itemView);

            judul = itemView.findViewById(R.id.tv_judul);
            isbn = itemView.findViewById(R.id.tv_isbn);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), ""+bukusList.get(getAdapterPosition()).getIdbuku(), Toast.LENGTH_SHORT).show();

            if (listener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            }

        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}