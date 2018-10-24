package id.ac.unej.ilkom.ods.buangin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Model.Voucher;
import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.Util.Util;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder> {
    private Context context;
    private List<Voucher> list;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mitra_daftar_voucher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Voucher model = list.get(position);
//        if (model != null) {
//
//        }
//
//        StorageReference fotoReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUrl_foto());
//        fotoReference.getBytes(Util.MAX_SIZE).addOnCompleteListener(new OnCompleteListener<byte[]>() {
//            @Override
//            public void onComplete(@NonNull Task<byte[]> task) {
//                if (task.isSuccessful()) {
//                    Bitmap foto = BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length);
//                    holder.img.setImageBitmap(foto);
//                } else {
//                    Log.w("VoucherAdapter", "Gagal Mendownload Foto" + task.getException());
//                }
//            }
//
//        });

        holder.kuota.setText(model.getJumlahKuota());
        holder.judul.setText(model.getNamaVoucher());
        holder.harga.setText(model.getHargaPoin());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView kuota;
        public TextView judul;
        public TextView harga;


        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_voucher);
            kuota = (TextView) itemView.findViewById(R.id.kuota_voucher);
            judul = (TextView) itemView.findViewById(R.id.judul_voucher);
            harga = (TextView) itemView.findViewById(R.id.harga_voucher);
        }
    }

    public VoucherAdapter(Context context, List<Voucher> list) {
        this.context = context;
        this.list = list;
    }
}
