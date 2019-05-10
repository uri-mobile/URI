package com.example.penjadwalan.Activities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.penjadwalan.Activities.Activities.Anggotas;
import com.example.penjadwalan.R;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.AnggotaViewHolder> {
    private Context mcontext;
    private List<Anggotas> anggotasList;

    public adapter(Context mcontext, List<Anggotas> anggotasList) {
        this.mcontext = mcontext;
        this.anggotasList = anggotasList;
    }

    @NonNull
    @Override
    public AnggotaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.anggotaitem,null);
        return new AnggotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnggotaViewHolder anggotaViewHolder, int i) {
        Anggotas anggotass= anggotasList.get(i);

        //image
        Glide.with(mcontext).load(anggotass.getImg()).into(anggotaViewHolder.imageView);

        anggotaViewHolder.name.setText(anggotass.getNama());
        anggotaViewHolder.angkatan.setText(String.valueOf(anggotass.getAngkatan()));//konfert double t string
        anggotaViewHolder.prodi.setText(anggotass.getProdi());
    }

    @Override
    public int getItemCount() {
        return anggotasList.size();
    }

    public class AnggotaViewHolder extends RecyclerView.ViewHolder{
        TextView name, angkatan,prodi;
        ImageView imageView;
        public AnggotaViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.namaangg);
            angkatan = itemView.findViewById(R.id.angkatann);
            prodi = itemView.findViewById(R.id.prodi);
            imageView = itemView.findViewById(R.id.profilAngg);
        }
    }
}
