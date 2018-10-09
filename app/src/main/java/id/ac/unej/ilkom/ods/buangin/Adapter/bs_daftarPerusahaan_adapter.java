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

import id.ac.unej.ilkom.ods.buangin.Model.bs_daftarPerusahaan_model;
import id.ac.unej.ilkom.ods.buangin.Model.m_daftarVoucher_model;
import id.ac.unej.ilkom.ods.buangin.R;

public class bs_daftarPerusahaan_adapter extends RecyclerView.Adapter<bs_daftarPerusahaan_adapter.MyViewHolder> {
    private Context context;
    private List<bs_daftarPerusahaan_model> modelList;

    public bs_daftarPerusahaan_adapter(Context context, List<bs_daftarPerusahaan_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_bank_daftar_perusahaan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        bs_daftarPerusahaan_model model = modelList.get(position);
        holder.gambar.setImageResource(model.getGambar());
        holder.alamat.setText(model.getAlamat());
        holder.pemilik.setText(model.getPemilik());
        holder.perusahaan.setText(model.getPerusahaan());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView perusahaan, pemilik, alamat;
        ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            perusahaan = (TextView) itemView.findViewById(R.id.bank_nama_perusahaan);
            pemilik = (TextView) itemView.findViewById(R.id.bank_pemilik_perusahaan);
            alamat = (TextView) itemView.findViewById(R.id.bank_alamat_perusahaan);
            gambar = (ImageView) itemView.findViewById(R.id.bank_gambar_perusahaan);
        }
    }
}
