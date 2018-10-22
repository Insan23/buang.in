package id.ac.unej.ilkom.ods.buangin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.ac.unej.ilkom.ods.buangin.Model.p_ubahHarga_model;
import id.ac.unej.ilkom.ods.buangin.R;

public class p_ubahHarga_adapter extends RecyclerView.Adapter<p_ubahHarga_adapter.MyViewHolder> {
    private Context context;
    private List<p_ubahHarga_model> modelList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perusahaan_ubah_harga, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        p_ubahHarga_model model = modelList.get(position);
        holder.gambar.setImageResource(model.getGambar());
        holder.alamat.setText(model.getAlamat());
        holder.harga.setText(model.getHarga());
        holder.pemilik.setText(model.getPemilik());
        holder.jenisSampah.setText(model.getJenisSampah());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView jenisSampah, pemilik, harga, alamat;
        ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            jenisSampah = (TextView) itemView.findViewById(R.id.perusahaan_jenisSampah_ubahHarga);
            pemilik = (TextView) itemView.findViewById(R.id.perusahaan_pemilik_ubahHarga);
            harga = (TextView) itemView.findViewById(R.id.perusahaan_harga_ubahHarga);
            alamat = (TextView) itemView.findViewById(R.id.perusahaan_alamat_ubahHarga);
            gambar = (ImageView) itemView.findViewById(R.id.perusahaan_gambar_ubahHarga);
        }
    }

    public p_ubahHarga_adapter(Context context, List<p_ubahHarga_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }
}
