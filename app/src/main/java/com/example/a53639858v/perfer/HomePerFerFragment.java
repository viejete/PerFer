package com.example.a53639858v.perfer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class HomePerFerFragment extends Fragment {

    View view;
    private Button enter;
    private GridView gridView;
    private NotesAdapter gridAdapter;
    private ArrayList<Nota> notas;
    private String mCurrentPhotoPath;
    private final int REQUEST_PHOTO = 1;

    public HomePerFerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_per_fer, container, false);

        notas = new ArrayList<>();
        gridAdapter = new NotesAdapter(getContext(), notas);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference("message");

        enter = (Button) view.findViewById(R.id.buttonEnter);
        final EditText fieldText = (EditText) view.findViewById(R.id.enterText);
        gridView = (GridView) view.findViewById(R.id.gridView);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldText.getText().toString().trim().length() > 0) {
                    DatabaseReference ref = myRef.push();
                    ref.setValue(fieldText.getText().toString());
                    notas.add(new Nota(null, fieldText.getText().toString()));
                    fieldText.getText().clear();

                }
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postData : dataSnapshot.getChildren()) {
                    String message = postData.getValue(String.class);
                    //notas.add(new Nota("" , message));
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (notas.get(position).getUrlImage() == null) {

                    dispatchTakePictureIntent();

                }
            }
        });

        return view;
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // sufix
                storageDir      // direcotry
        );

        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT , Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent , REQUEST_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri seleccio = data.getData();
                    String[] columna = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContext().getContentResolver().query(
                            seleccio, columna, null, null, null);
                    cursor.moveToFirst();

                    int indexColumna = cursor.getColumnIndex(columna[0]);
                    String rutaFitxer = cursor.getString(indexColumna);
                    cursor.close();
                    mCurrentPhotoPath = rutaFitxer;

                }

        }
    }
}
