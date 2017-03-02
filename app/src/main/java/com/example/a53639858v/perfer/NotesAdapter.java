package com.example.a53639858v.perfer;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Nota> {


    public NotesAdapter(Context context, int resource, ArrayList<Nota> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

//        // Obtenim l'objecte en la possició corresponent
//        Movie movie = getItem(position);
//        Log.w("XXXX", movie.toString());
//
//        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
//        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
//        if (convertView == null) {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.lv_pelis_row, parent, false);
//        }
//
//        // Unim el codi en les Views del Layout
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvCriticsScore = (TextView) convertView.findViewById(R.id.tvCriticsScore);
//        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);
//
//        // Fiquem les dades dels objectes (provinents del JSON) en el layout
//        tvTitle.setText(movie.getTitle());
//        tvCriticsScore.setText("Score: " + movie.getCritics_score() + "%");
//        Glide.with(getContext()).load(movie.getPosterUrl()).into(ivPosterImage);
//
//        // Retornem la View replena per a mostrarla
//        return convertView;

        Nota nota = getItem(i);

        Log.w("XXXX", nota.toString());

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.grid_nota, parent, false);
        }


        TextView textView = (TextView) convertView.findViewById(R.id.textGrid);
        ImageView imageGrid = (ImageView) convertView.findViewById(R.id.imageGrid);

        textView.setText(nota.getText());

        if (!nota.getUrlImage().equals("")) {
            imageGrid.setImageURI(Uri.parse(nota.getUrlImage()));
            Log.w("XXXX" , nota.getUrlImage());
        } else {
            imageGrid.setImageDrawable(ContextCompat.getDrawable(getContext() , R.drawable.no_image));
        }

        return convertView;
    }

}
