package com.semwal.mvvmdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class Common {
    private static Common common=null;

    static AlertDialog b;
    AlertDialog.Builder dialogBuilder;


    private Common(){
    }
    public static Common getInstance(){
        if (common==null) {
            common = new Common();
        }
        return common;
    }

    public void ShowProgressDialog(Activity activity){
        dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = (LayoutInflater)activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View dialogView = inflater.inflate(R.layout.progress_dialog_layout, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();
        b.show();
    }
    public static void hideProgressDialog(){

        b.dismiss();
    }
}
