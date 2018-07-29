package com.zadi.zadi.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.zadi.zadi.R;

public class DialogUtils {



    public static AlertDialog showAlertDialog(Context context, String message,
                                              DialogInterface.OnClickListener negativeClickListener) {
        // create the dialog builder & set message
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(message);
        // check negative click listener
        if (negativeClickListener != null) {
            // not null
            // add negative click listener
            dialogBuilder.setNegativeButton(context.getString(R.string.yes), negativeClickListener);
        } else {
            // null
            // add new click listener to dismiss the dialog
            dialogBuilder.setNegativeButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        // create and show the dialog

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        return dialog;
    }
}
