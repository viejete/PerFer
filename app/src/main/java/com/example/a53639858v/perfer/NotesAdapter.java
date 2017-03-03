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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NotesAdapter extends ArrayAdapter<Nota> {


    public NotesAdapter(Context context, int resource, ArrayList<Nota> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

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

            Glide
                    .with(getContext())
                    .load(nota.getUrlImage())
                    .centerCrop()
                    .into(imageGrid);

            Log.w("XXXX" , nota.getUrlImage());
        } else {
            imageGrid.setImageDrawable(ContextCompat.getDrawable(getContext() , R.drawable.no_image));
        }

        return convertView;
    }

}
