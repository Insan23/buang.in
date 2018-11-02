package id.ac.unej.ilkom.ods.buangin.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static final int WRITE_EXTERNAL = 101;

    public static final int REQUEST_IMAGE_CAPTURE = 102;

    public static final long MB = 20 * 1024 * 1024;

    public Util() {

    }

    public static String md5(String input) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String tanggalSekarang() {
        String tanggal = "";
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy-HH:mm");
        tanggal = formatter.format(new Date());
        return tanggal;
    }

    public static String waktuEpochSekarang() {
        String tanggal = "";
        SimpleDateFormat formatter = new SimpleDateFormat("mmSS");
        tanggal = formatter.format(new Date());
        return tanggal;
    }

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void alertDialog(Context context, String title, String msg, String msgPositive, DialogInterface.OnClickListener positive, String msgNegative, DialogInterface.OnClickListener negative) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        if (!title.equals(null) || !title.equalsIgnoreCase("")) {
            dialog.setTitle(title);
        }
        if (!msgPositive.equals(null) || !msgPositive.equalsIgnoreCase("")) {
            dialog.setPositiveButton(msgPositive, positive);
        }

        if (!msgNegative.equals(null) || !msgNegative.equalsIgnoreCase("")) {
            dialog.setNegativeButton(msgNegative, negative);
        }

        dialog.show();
    }
}
