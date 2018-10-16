package id.ac.unej.ilkom.ods.buangin.View.Volunteer;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.Welcome;

import static android.app.Activity.RESULT_OK;
import static id.ac.unej.ilkom.ods.buangin.Welcome.WRITE_EXTERNAL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampahkuFragment extends Fragment {

    //    private ImageButton buka_kamera;
    private static final int req = 1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;
    private FloatingActionButton buka_kamera;
    private ImageView img_hasil;
    String name = "";

    private TabVoucherFragment voucherFragment;
    private TabPoinFragment poinFragment;

    public SampahkuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeVolunteer) getActivity()).setActionBarTitle("Sampahku");
        View view = inflater.inflate(R.layout.fragment_volunteer_sampah, container, false);

        img_hasil = (ImageView) view.findViewById(R.id.img_scan);
        buka_kamera = (FloatingActionButton) view.findViewById(R.id.btn_sampah_buka_kamera);
        buka_kamera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                cekIzin();
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), HomeVolunteer.REQUEST_IMAGE_CAPTURE);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HomeVolunteer.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d("CAMERA : ", "SUCCESS");
            Bitmap thumb = (Bitmap) data.getExtras().get("data");
            Uri fotoURI = getImageUri(getContext(), thumb);

            DialogSampah dialog = new DialogSampah();

            dialog.setImg(thumb);
            dialog.setImgUri(fotoURI);
            dialog.show(getChildFragmentManager(), "Konfirmasi Sampah");
            Toast.makeText(getContext(), "sukses", Toast.LENGTH_LONG).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);

        return Uri.parse(path);
    }

    private void cekIzin() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL);
        }
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

