package com.securitycar.app;

import android.app.Activity;
import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.securitycar.R;
import com.tapadoo.alerter.Alert;
import com.tapadoo.alerter.Alerter;

public class AppConfig {

    //Dialog Progress Alerts
    public static MaterialDialog alertProgressCustom(Context context, String msj, int color, boolean visible){
        MaterialDialog dialog;
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                //.title(titulo)
                .progress(true, 0)
                .content(msj)
                .widgetColorRes(color)
                ;
        dialog = builder.build();
        if (visible){
            dialog.show();
        }else {
            dialog.dismiss();
        }
        return dialog;
    }

    public static Alert alertsCustom(Context context, String titulo, String msj, String tipo, int time, int icono){



        Alert a = null;
        if (tipo.equals("success")){
            if (time != 0) {
                a = Alerter.create((Activity) context)
                        .setTitle(titulo)
                        .setText(msj)
                        .setBackgroundColorRes(R.color.AlertVerde)
                        .setIcon(R.drawable.ic_check_circle_white_24dp)
                        .setDuration(time)
                        .show();
            }else {
                a = Alerter.create((Activity) context)
                        .setTitle(titulo)
                        .setText(msj)
                        .setBackgroundColorRes(R.color.AlertVerde)
                        .setIcon(R.drawable.ic_check_circle_white_24dp)
                        .setDuration(3000)
                        .show();
            }

        }if (tipo.equals("error")){
            if (time != 0) {
                a = Alerter.create((Activity) context)
                        .setTitle(titulo)
                        .setText(msj)
                        .setBackgroundColorRes(R.color.AlertRojo)
                        .setIcon(R.drawable.ic_error_white_24dp)
                        .setDuration(time)
                        .show();
            }else {
                a = Alerter.create((Activity) context)
                        .setTitle(titulo)
                        .setText(msj)
                        .setBackgroundColorRes(R.color.AlertRojo)
                        .setIcon(R.drawable.ic_error_white_24dp)
                        .setDuration(3000)
                        .show();
            }

        }if (tipo.equals("info")){
            if (time != 0) {
                a = Alerter.create((Activity) context)
                        .setTitle(titulo)
                        .setText(msj)
                        .setBackgroundColorRes(R.color.AlertAzul)
                        .setIcon(R.drawable.ic_help_white_24dp)
                        .setDuration(time)
                        .show();
            }else {
                a = Alerter.create((Activity) context)
                        .setTitle(titulo)
                        .setText(msj)
                        .setBackgroundColorRes(R.color.AlertAzul)
                        .setIcon(R.drawable.ic_help_white_24dp)
                        .setDuration(3000)
                        .show();
            }
        }
        return a;
    }




}
