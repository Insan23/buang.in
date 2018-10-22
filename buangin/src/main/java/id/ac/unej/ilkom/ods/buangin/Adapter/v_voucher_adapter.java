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

import id.ac.unej.ilkom.ods.buangin.Model.v_voucher_model;
import id.ac.unej.ilkom.ods.buangin.R;

public class v_voucher_adapter extends RecyclerView.Adapter<v_voucher_adapter.MyViewHolder> {
    private Context context;
    private List<v_voucher_model> modelList;


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_volunteer_voucher, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        v_voucher_model model = modelList.get(position);
        holder.namaMitra.setText(model.getNamaMitra());
        holder.poin.setText(model.getPoin());
        holder.jumlah.setText(model.getJumlah());
        holder.gambar.setImageResource(model.getGambar());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaMitra, poin, jumlah;
        public ImageView gambar;

        public MyViewHolder(View itemView) {
            super(itemView);
            namaMitra = (TextView) itemView.findViewById(R.id.volunteer_namaMitra_voucher);
            poin = (TextView) itemView.findViewById(R.id.volunteer_poin_voucher);
            jumlah = (TextView) itemView.findViewById(R.id.volunteer_jumlahVoucher_voucher);
            gambar = (ImageView) itemView.findViewById(R.id.volunteer_gambar_voucher);
        }
    }

    public v_voucher_adapter(Context context, List<v_voucher_model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }
}
