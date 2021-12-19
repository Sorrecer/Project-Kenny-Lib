package com.example.mobapps_kennylib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter {

    List<Buku> fetchDataList;
    private AdapterView.OnItemClickListener mListener;

    public Adapter(List<Buku> fetchDataList) {
        this.fetchDataList = fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buku,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view,mListener);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        Buku buku = fetchDataList.get(position);
        viewHolderClass.judul.setText(buku.getJudul());
        viewHolderClass.pengarang.setText(buku .getPengarang());
    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView nim,nama,jurusan,angkatan;

        public ViewHolderClass(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            nim=itemView.findViewById(R.id.tv_buku);
            nama=itemView.findViewById(R.id.tv_penulis);

            //Dilakukan agar item pada list dapat berpindah ke activity lain
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
