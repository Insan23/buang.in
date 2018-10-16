package id.ac.unej.ilkom.ods.buangin.View.Volunteer;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.ac.unej.ilkom.ods.buangin.Model.Sampah;
import id.ac.unej.ilkom.ods.buangin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogSampah extends DialogFragment {

    private static final String TAG = "Dialog Sampah";

    private ImageView imgPreview;
    private TextView kode, tanggal;
    private Button kirim;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;
    private StorageReference stRef;

    private Bitmap img;
    private Uri imgUri;

    public DialogSampah() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_sampah, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference();

        imgPreview = (ImageView) view.findViewById(R.id.img_preview);
        kode = (TextView) view.findViewById(R.id.kode_sampah);
        tanggal = (TextView) view.findViewById(R.id.tanggal_sampah);
        kirim = (Button) view.findViewById(R.id.kirim_sampah);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String uid = user.getUid();
        String tanggalAwal = "";
        String tanggalAkhir = "";

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
        tanggalAwal = formatter.format(new Date());

        Date date = null;
        try {
            date = formatter.parse(tanggalAwal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendarPlus = Calendar.getInstance();
        calendarPlus.setTime(date);
        calendarPlus.add(Calendar.HOUR, 2);
        Date akhir = calendarPlus.getTime();

        tanggalAkhir = formatter.format(akhir);

        final String kodeMD5 = md5(tanggalAwal + tanggalAkhir + uid);
        Log.d("DialogSampah", "Tanggal Awal: " + tanggalAwal);
        Log.d("DialogSampah", "Tanggal Akhir: " + tanggalAkhir);
        Log.d("DialogSampah", "Kode: " + kodeMD5);

        kode.setText(kodeMD5);
        tanggal.setText(tanggalAwal);
        imgPreview.setImageBitmap(img);

        final String tglAwal = tanggalAwal;
        final String tglAkhir = tanggalAkhir;

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sampah tempSampah = new Sampah(kodeMD5, null, tglAwal, tglAkhir, uid, Sampah.VERIFIKASI_MENUNGGU, null);
                dbRef.child("dataSampah").child(uid).push().setValue(tempSampah, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            String key = databaseReference.getKey();
                            StorageReference stor = FirebaseStorage.getInstance()
                                    .getReference(uid)
                                    .child(key);
                            kirimDB(stor, imgUri, key, kodeMD5, tglAwal, tglAkhir, uid);
                        } else {
                            Log.w(TAG, "Database Error: " + databaseError.getDetails());
                        }
                    }
                });
                Toast.makeText(getContext(), "Sukses", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
    }

    private String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
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

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }

    private void kirimDB(final StorageReference storageReference, Uri uri, final String key, final String kode, final String tAwal, final String tAkhir, final String uid) {
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    String url = storageReference.getDownloadUrl().toString();
                    Log.d(TAG, "Download URL : " + url);
                    Sampah sampah = new Sampah(kode, url, tAwal, tAkhir, uid, Sampah.VERIFIKASI_MENUNGGU, null);
//                    dbRef.child("dataSampah").child(key).setValue(sampah);
                } else {
                    Toast.makeText(getContext(), "Gagal Mengupload Foto : " + task.getException(), Toast.LENGTH_LONG).show();;
                    Log.w(TAG, "Image upload task was not successful.",
                            task.getException());
                }
            }
        });
    }

}
