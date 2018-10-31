package id.ac.unej.ilkom.ods.buangin.adapter;

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

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;

import static id.ac.unej.ilkom.ods.buangin.util.Util.MB;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder> {
    private List<ModelVoucher> list;
    private OnItemClickListener listener;

    public VoucherAdapter(Context context, List<ModelVoucher> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mitra_daftar_voucher, parent, false);
        return new MyViewHolder(view);
    }

    public interface OnItemClickListener {
        void onItemClick(ModelVoucher model);
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

        public void bind(final ModelVoucher model, final OnItemClickListener listener) {
            kuota.setText(model.getJumlahKuota());
            judul.setText(model.getNamaVoucher());
            harga.setText(model.getHargaPoin());

            StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUrl_foto());
            ref.getBytes(MB).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                @Override
                public void onComplete(@NonNull Task<byte[]> task) {
                    if (task.isSuccessful()) {
                        Log.d("InfoAdap", "download foto");
                        Bitmap fotoDownload = BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length);
                        img.setImageBitmap(fotoDownload);
                        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    } else {
                        Log.w(VoucherAdapter.class.getSimpleName(), "gagal download foto:" + task.getException());
                        img.setImageResource(R.drawable.download_failed);
                        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);
                }
            });
        }
    }
}
