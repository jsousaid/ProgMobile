package br.fatec.projeto.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.WindowManager;


public class Util {

    public static void exibirMensagem(Context context, String msg) {
        final AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("Controle de Produtos");
        dlg.setCancelable(false);
        dlg.setMessage(msg);
        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlg.show();
    }
}
