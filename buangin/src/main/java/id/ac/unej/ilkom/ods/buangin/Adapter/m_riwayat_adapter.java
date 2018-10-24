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
import id.ac.unej.ilkom.ods.buangin.model.m_riwayat_model;

public class m_riwayat_adapter extends RecyclerView.Adapter<m_riwayat_adapter.MyViewHolder> {
    private Context context;
    private List<m_riwayat_model> modelList;

    public m_riwayat_adapter(Context context, List<m_riwayat_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mitra_riwayat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        m_riwayat_model model = modelList.get(position);
        holder.gambar.setImageResource(model.getGambar());
        holder.poin.setText(model.getPoin());
        holder.namaMitra.setText(model.getNamaMitra());
        holder.tanggal.setText(model.getTanggalTukar());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView namaMitra, poin, tanggal;
        ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            namaMitra = (TextView) itemView.findViewById(R.id.mitra_namaMitra_riwayat);
            poin = (TextView) itemView.findViewById(R.id.mitra_poin_riwayat);
            tanggal = (TextView) itemView.findViewById(R.id.mitra_tanggal_riwayat);
            gambar = (ImageView) itemView.findViewById(R.id.mitra_gambar_riwayat);
        }
    }
}
