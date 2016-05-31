package controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.akhyanvaidya.secusafe.LoginActivity;


/**
 * Created by akhyanvaidya on 24/05/16.
 * Execute for any session related method
 */
public class SessionControl {
    SharedPreferences shareLogin, shareRemember, shareCheck;


    public static void HeaderUsername(TextView inUser, Context ctx){
        SharedPreferences shareCheck= PreferenceManager.getDefaultSharedPreferences(ctx);
        String name= shareCheck.getString("Username", "");
        inUser.setText("Username: " + name);
    }
    //Logout from ActionBar Menu
    public static void SessionEnd(final Context ctx){
        AlertDialog.Builder askLogOut = new android.app.AlertDialog.Builder(ctx);
        askLogOut.setMessage("Do you want to logout?");
        /*yes -> quit to login
         no -> close the dialog box
         */
        askLogOut.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ClearRemember(ctx);
                Intent closeHome = new Intent(ctx, LoginActivity.class);
                ctx.startActivity(closeHome);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //create the alert menu
        AlertDialog alertDialog= askLogOut.create();
        alertDialog.show();


    }

    public static void ClearRemember(Context ctx){  //clear shared pref
        SharedPreferences shareLogin= PreferenceManager.getDefaultSharedPreferences(ctx);
        shareLogin.edit().remove("Username").commit();
        shareLogin.edit().remove("Password").commit();
    }


}
