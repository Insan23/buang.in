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
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucherVolunteer;

import static id.ac.unej.ilkom.ods.buangin.util.Util.MB;

public class RiwayatAdapterMitra extends RecyclerView.Adapter<RiwayatAdapterMitra.ViewHolder> {

    private Context context;
    private List<ModelVoucherVolunteer> list;

    public RiwayatAdapterMitra(Context context, List<ModelVoucherVolunteer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mitra_riwayat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView judul;
        public TextView harga;
        public TextView tanggal;
        public TextView kode;
        public TextView status;
        private TextView namaVolunteer;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mitra_gambar_riwayat);
            judul = itemView.findViewById(R.id.volunteer_judul_voucher);
            namaVolunteer = itemView.findViewById(R.id.teks_nama_volunteer);
            harga = itemView.findViewById(R.id.teks_poin);
            tanggal = itemView.findViewById(R.id.mitra_tanggal_tukar);
            status = itemView.findViewById(R.id.status_voucher);
            kode = itemView.findViewById(R.id.mitra_kode);
        }

        public void bind(ModelVoucherVolunteer model) {
            judul.setText(model.getNamaVoucher());
            namaVolunteer.setText(model.getNamaVolunteer());
            harga.setText(model.getHargaPoin());
            tanggal.setText(model.getTanggal());
            status.setText(model.getStatusVoucher());
            kode.setText(model.getKodeVoucher());

            StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUrl_foto());
            ref.getBytes(MB).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                @Override
                public void onComplete(@NonNull Task<byte[]> task) {
                    if (task.isSuccessful()) {
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
        }
    }
}
