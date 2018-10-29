package id.ac.unej.ilkom.ods.buangin.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucher;

public class BeliVoucherAdapter extends RecyclerView.Adapter<BeliVoucherAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ModelVoucher model);
    }

    private List<ModelVoucher> listVoucher;
    private OnItemClickListener listener;

    public BeliVoucherAdapter(List<ModelVoucher> listVoucher, OnItemClickListener lisetner) {
        this.listener = lisetner;
        this.listVoucher = listVoucher;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_volunteer_voucher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.bind(listVoucher.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listVoucher.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgVoucher;
        public TextView namaMitra;
        public TextView namaVoucher;
        public TextView hargaVoucher;
        public TextView kuotaVoucher;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgVoucher = (ImageView) itemView.findViewById(R.id.img_voucher);
            kuotaVoucher = (TextView) itemView.findViewById(R.id.kuota_voucher);
            namaVoucher = (TextView) itemView.findViewById(R.id.judul_voucher);
            namaMitra = (TextView) itemView.findViewById(R.id.nama_mitra);
            hargaVoucher = (TextView) itemView.findViewById(R.id.harga_voucher);
        }

        public void bind(final ModelVoucher model, final OnItemClickListener listener) {
            //        StorageReference fotoReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.getUrl_foto());
//        fotoReference.getBytes(Util.MAX_SIZE).addOnCompleteListener(new OnCompleteListener<byte[]>() {
//            @Override
//            public void onComplete(@NonNull Task<byte[]> task) {
//                if (task.isSuccessful()) {
//                    Bitmap foto = BitmapFactory.decodeByteArray(task.getResult(), 0, task.getResult().length);
//                    holder.imgVoucher.setImageBitmap(foto);
//                } else {
//                    Log.w("VoucherAdapter", "Gagal Mendownload Foto" + task.getException());
//                }
//            }
//
//        });
            namaMitra.setText(model.getNamaMitra());
            namaVoucher.setText(model.getNamaVoucher());
            hargaVoucher.setText(model.getHargaPoin());
            kuotaVoucher.setText(model.getJumlahKuota());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model);
                }
            });
        }
    }

}
