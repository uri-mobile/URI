package com.example.penjadwalan.Activities.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.penjadwalan.Activities.Activities.Prestasis;
import com.example.penjadwalan.R;

import java.util.List;

public class adapterP extends RecyclerView.Adapter<adapterP.PViewHolder> {
    private Context mcontext;
    private List<Prestasis> prestasisList;

    public adapterP(Context mcontext, List<Prestasis> prestasisList) {
        this.mcontext = mcontext;
        this.prestasisList = prestasisList;
    }

    @NonNull
    @Override
    public PViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.prestasiitem,null);
        return new adapterP.PViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PViewHolder pViewHolder, int i) {
        Prestasis prestasis = prestasisList.get(i);

        pViewHolder.event.setText(prestasis.getEvent());
        pViewHolder.emas.setText(prestasis.getEmas());
        pViewHolder.perak.setText(prestasis.getPerak());
        pViewHolder.perunggu.setText(prestasis.getPerunggu());
    }

    @Override
    public int getItemCount() {
        return prestasisList.size();
    }


    public class PViewHolder extends RecyclerView.ViewHolder{
        TextView event, emas,perak, perunggu;

        public PViewHolder(@NonNull View itemView) {
            super(itemView);

            event = itemView.findViewById(R.id.namaevent);
            emas = itemView.findViewById(R.id.emas);
            perak = itemView.findViewById(R.id.perak);
            perunggu = itemView.findViewById(R.id.perunggu);
        }
    }
}
