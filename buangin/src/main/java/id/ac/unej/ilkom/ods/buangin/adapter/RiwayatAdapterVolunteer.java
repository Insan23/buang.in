package id.ac.unej.ilkom.ods.buangin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.R;
import id.ac.unej.ilkom.ods.buangin.model.ModelVoucherVolunteer;

public class RiwayatAdapterVolunteer extends RecyclerView.Adapter<RiwayatAdapterVolunteer.MyViewHolder> {
    private Context context;
    private List<ModelVoucherVolunteer> list;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_volunteer_riwayat, parent, false);
        return new RiwayatAdapterVolunteer.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelVoucherVolunteer model = list.get(position);
        holder.namaMitra.setText(model.getNamaMitra());
        holder.status.setText(model.getStatusVoucher());
        holder.harga.setText(model.getHargaPoin());
        holder.judul.setText(model.getNamaVoucher());
        holder.kode.setText(model.getKodeVoucher());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView kode;
        public TextView status;
        public TextView namaMitra;
        public TextView harga;
        public TextView judul;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.voulunteer_img_voucher);
            kode = (TextView) itemView.findViewById(R.id.volunteer_kode_voucher);
            status = (TextView) itemView.findViewById(R.id.volunteer_status_voucher);
            namaMitra = (TextView) itemView.findViewById(R.id.volunteer_mitra_voucher);
            harga = (TextView) itemView.findViewById(R.id.volunteer_harga_voucher);
            judul = (TextView) itemView.findViewById(R.id.volunteer_judul_voucher);
        }
    }

    public RiwayatAdapterVolunteer(Context context, List<ModelVoucherVolunteer> list) {
        this.context = context;
        this.list = list;
    }
}
