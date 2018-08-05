package rahul.myapplication.ui.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import rahul.myapplication.R;


/**
 * Created by rahul on 4/4/18
 */

public class UiUtils {


    public  AlertDialog showProgressBar(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.create();
        View dialogLayout;
        dialogLayout = LayoutInflater.from(context).inflate(R.layout.custom_progress_bar, null);
        dialog.setView(dialogLayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
