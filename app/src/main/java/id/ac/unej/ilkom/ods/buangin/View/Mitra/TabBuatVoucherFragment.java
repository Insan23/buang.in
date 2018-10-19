package id.ac.unej.ilkom.ods.buangin.View.Mitra;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

import id.ac.unej.ilkom.ods.buangin.Model.Voucher;
import id.ac.unej.ilkom.ods.buangin.R;

import static android.app.Activity.RESULT_OK;
import static id.ac.unej.ilkom.ods.buangin.Util.Util.REQUEST_IMAGE_CAPTURE;
import static id.ac.unej.ilkom.ods.buangin.Util.Util.WRITE_EXTERNAL;

public class TabBuatVoucherFragment extends Fragment {

    private EditText namaProduk, deskripsi, poin, kuota;
    private ImageView voucher;
    private Button tambah, batal;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference dbRef;

    private Bitmap hasilFoto;
    private Uri uriFoto;

    public TabBuatVoucherFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mitra_tab_buat_voucher, container, false);


        namaProduk = (EditText) view.findViewById(R.id.input_voucher_namaProduk);
        deskripsi = (EditText) view.findViewById(R.id.input_ubahHarga_deskripsi);
        poin = (EditText) view.findViewById(R.id.input_voucher_poin);
        kuota = (EditText) view.findViewById(R.id.input_voucher_jumlahKuota);
        voucher = (ImageView) view.findViewById(R.id.foto_produk_voucher);

        batal = (Button) view.findViewById(R.id.btn_voucher_batal);
        tambah = (Button) view.findViewById(R.id.btn_voucher_tambah);

        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL);
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);
                } else {
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNama = namaProduk.getText().toString().trim();
                String strDeskripsi = deskripsi.getText().toString().trim();
                String strPoin = poin.getText().toString().trim();
                String strKuota = kuota.getText().toString().trim();

                final String uid = auth.getUid();

                dbRef = FirebaseDatabase.getInstance().getReference();

                boolean kirim = false;

                if (strNama.isEmpty()) {
                    namaProduk.requestFocus();
                    namaProduk.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (strDeskripsi.isEmpty()) {
                    deskripsi.requestFocus();
                    deskripsi.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (strPoin.isEmpty()) {
                    poin.requestFocus();
                    poin.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (strKuota.isEmpty()) {
                    kuota.requestFocus();
                    kuota.setError("Isi terlebih dahulu");
                    kirim = false;
                } else {
                    kirim = true;
                }

                if (kirim) {
                    Voucher voucher = new Voucher(null, strNama, strDeskripsi, strPoin, strKuota, uid, Voucher.VOUCHER_BERLAKU);

                    dbRef.child("dataVoucher").push().setValue(voucher, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError dbErr, @NonNull DatabaseReference dbRef) {
                            if (dbErr == null) {
                                Toast.makeText(getContext(), "Berhasil Membuat Voucher", Toast.LENGTH_LONG).show();
                                String key = dbRef.getKey();
                                StorageReference stor = FirebaseStorage.getInstance()
                                        .getReference("voucher_mitra")
                                        .child(uid)
                                        .child(key);

                            } else {
                                Toast.makeText(getContext(), "Gagal Membuat Voucher", Toast.LENGTH_LONG).show();
                                Log.w("TabBuatVoucherFragment", dbErr.getDetails());
                            }
                        }
                    });
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Voucher");
                builder.setMessage("Batal menambahkan voucher?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        namaProduk.setText("");
                        deskripsi.setText("");
                        poin.setText("");
                        kuota.setText("");
                        voucher.setImageResource(R.drawable.bg_img_voucher);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d("CAMERA : ", "SUCCESS");
            hasilFoto = (Bitmap) data.getExtras().get("data");
            uriFoto = getImageUri(getContext(), hasilFoto);

            voucher.setImageBitmap(hasilFoto);

            Toast.makeText(getContext(), "sukses", Toast.LENGTH_LONG).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);

        return Uri.parse(path);
    }

    private void kirimStorage(final StorageReference storageReference, Uri uri, final String key, final String kode, final String tAwal, final String tAkhir, final String uid) {
//        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if (task.isSuccessful()) {
//                    String url = storageReference.getDownloadUrl().toString();
//                    Log.d(TAG, "Download URL : " + url);
//                    Sampah sampah = new Sampah(kode, url, tAwal, tAkhir, uid, Sampah.VERIFIKASI_MENUNGGU, null);
////                    dbRef.child("dataSampah").child(key).setValue(sampah);
//                } else {
//                    Toast.makeText(getContext(), "Gagal Mengupload Foto : " + task.getException(), Toast.LENGTH_LONG).show();;
//                    Log.w(TAG, "Image upload task was not successful.",
//                            task.getException());
//                }
//            }
//        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("Fragment Sampahku", "Masuk resul");
        switch (requestCode) {
            case WRITE_EXTERNAL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Fragment Sampahku", "disetujui");
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
        }
    }
}
