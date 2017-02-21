package com.example.a53639858v.perfer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Nota> notas;

    public NotesAdapter (Context c , ArrayList<Nota> notas) {
        context = c;
        this.notas = notas;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

         final Nota nota = notas.get(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_nota , null);


            //ImageView imageView = (ImageView) gridView.findViewById(R.id.imageGrid);

            //imageView.setImageURI(Uri.parse(nota.getUrlImage()));
        } else {
            gridView = convertView;
        }

        TextView textView = (TextView) gridView.findViewById(R.id.textGrid);
        textView.setText(nota.getText());

        return gridView;
    }


    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
