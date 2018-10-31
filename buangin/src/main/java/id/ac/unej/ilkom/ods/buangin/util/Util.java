package id.ac.unej.ilkom.ods.buangin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static final String DATA_VOUCHER_REFERENCE = "dataVoucher";
    public static final String DATA_SAMPAH_REFERENCE = "dataSampah";
    public static final String DATA_BANK_SAMPAH_REFERENCE = "dataBankSampah";
    public static final String DATA_PERUSAHAAN_REFERENCE = "dataPerusahaan";
    public static final String DATA_PENGGUNA = "penguna";

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
        SimpleDateFormat formatter = new SimpleDateFormat("mmSSS");
        tanggal = formatter.format(new Date());
        return tanggal;
    }
}
