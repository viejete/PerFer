package com.example.a53639858v.perfer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;


public class MessageTask extends AsyncTask<Void,Void,Void> {

    private Context myContext;

    public MessageTask (Context context) {
        myContext = context;
    }

    @Override
    protected Void doInBackground(Void... params) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(myContext);
        dialog.setMessage("You need to grant the permissions to run the app!");
        dialog.setTitle("App Permissions");
        dialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        dialog.create().show();
        return null;
    }
}
