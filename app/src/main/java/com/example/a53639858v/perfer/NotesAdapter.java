package com.example.a53639858v.perfer;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NotesAdapter extends BaseAdapter {

    private Context context;
    private Nota[] notas;

    public NotesAdapter (Context c , Nota[] notas) {
        context = c;
        this.notas = notas;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

         Nota nota = (Nota) getItem(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_nota , null);

            TextView textView = (TextView) gridView.findViewById(R.id.textGrid);
            textView.setText(nota.getText());

            ImageView imageView = (ImageView) gridView.findViewById(R.id.imageGrid);

            imageView.setImageURI(Uri.parse(nota.getUrlImage()));
        } else {
            gridView = convertView;
        }

        //http://stackoverflow.com/questions/32578058/display-photo-in-gridview-taken-from-camera

        return gridView;
    }


    @Override
    public int getCount() {
        return 0;
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
