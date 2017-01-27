package com.example.a53639858v.perfer;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomePerFerFragment extends Fragment {

    View view;
    private ArrayList<String> notas = new ArrayList<>();
    private Button enter;
    //private EditText fieldText;
    private ListView lvNotas;
    private ArrayAdapter<String> adapter;

    public HomePerFerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_per_fer, container, false);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("message");
        //DatabaseReference id_todo = myRef.child(""+System.currentTimeMillis());

        final DatabaseReference ref = myRef.push();
        ref.setValue("Hola 123");

        //myRef.setValue("Hello, World!");
        //id_todo.setValue("Child!");

        enter = (Button) view.findViewById(R.id.buttonEnter);
        final EditText fieldText = (EditText) view.findViewById(R.id.enterText);
        lvNotas = (ListView) view.findViewById(R.id.lvNotas);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldText.getText().toString().trim().length() > 0) {
                    ref.setValue(fieldText.getText().toString());
                    fieldText.getText().clear();

                }
            }
        });

        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.lv_notas_row,
                R.id.tvNota,
                notas
        );

        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                adapter.add(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        lvNotas.setAdapter(adapter);

        return view;
    }


}
