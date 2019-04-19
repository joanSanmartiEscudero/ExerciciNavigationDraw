package com.example.carles.navigationdraw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class AdaptadorLlistaLlocs extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contextAdaptador;
    List list;

    public AdaptadorLlistaLlocs(Context context, List L) {
        this.contextAdaptador = context;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.list = L;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Lloc lloc = (Lloc) list.get(position);

        final View view = inflater.inflate(R.layout.element_llista_llocs, null);
        TextView nomLlocTextView = view.findViewById(R.id.nomLlocTextView),
                adressLlocTextView = view.findViewById(R.id.adressLlocTextView),
                telefonLLocTextView = view.findViewById(R.id.telefonLlocTextView);

        ImageView imatgeLloc = view.findViewById(R.id.imatgeLloc);
        RatingBar puntuacioLloc = view.findViewById(R.id.puntuacioLloc);

        nomLlocTextView.setText(lloc.getNom());
        adressLlocTextView.setText(lloc.getAddress());
        telefonLLocTextView.setText(String.valueOf(lloc.getTelefon()));
        imatgeLloc.setImageResource(R.drawable.ducky);
        puntuacioLloc.setRating(lloc.getValoracio());

        return view;
    }
}
