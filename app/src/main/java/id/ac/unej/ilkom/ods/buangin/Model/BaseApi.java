package id.ac.unej.ilkom.ods.buangin.Model;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import id.ac.unej.ilkom.ods.buangin.View.HalamanMasuk;

public class BaseApi {

    private static FirebaseAuth auth;
    private static FirebaseUser user;
    private static FirebaseStorage storage;
    private static FirebaseDatabase db;
    private static DatabaseReference dbRef;

    public static final String BASE_URL = "https://buang-in.firebaseio.com/";

    public static String login(String email, String password, final Activity activity, final String TAG) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    final String[] level = {""};
                    dbRef.child("pengguna").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                Pengguna pen = child.getValue(Pengguna.class);
                                level[0] = pen.getLevel();
//                                cekLevel(pen.getLevel());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(activity, "Username Atau Email Salah", Toast.LENGTH_LONG).show();
                            Log.w(TAG, "Login ERROR : " + databaseError.getDetails());
                        }
                    });

                } else {
                    if (cekJaringan(activity)) {

                    }
                    Toast.makeText(activity, "Gagal Masuk", Toast.LENGTH_LONG).show();
                    Log.w(TAG, "Gagal Masuk: " + task.getException());
                }
            }
        });
        return "kosong";
    }

    private static boolean cekJaringan(Activity activity) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
