package com.example.miskaa.view.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.miskaa.R;
import com.example.miskaa.model.CountryPojo;

import java.util.List;



public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private Context context;
    private List<CountryPojo> countryList = null;

    public MainAdapter(List<CountryPojo> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.country_item, parent, false);
        return new MyViewHolder(view, context);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        CountryPojo countryPojo = countryList.get(position);
        holder.bind(countryPojo);
    }

    @Override
    public int getItemCount() {
        if (countryList == null || countryList.isEmpty()){
            return 0;
        }else{
            return countryList.size();
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private TextView tvCountryName = itemView.findViewById(R.id.tvCountryName);
        private TextView tvCapital = itemView.findViewById(R.id.tvCapital);
        private TextView tvRegion = itemView.findViewById(R.id.tvRegion);
        private TextView tvSubregion = itemView.findViewById(R.id.tvSubregion);
        private TextView tvPopulation = itemView.findViewById(R.id.tvPopulation);
        private TextView tvBorder = itemView.findViewById(R.id.tvBorder);
        private TextView tvLang = itemView.findViewById(R.id.tvLang);
        private ImageView ivFlag = itemView.findViewById(R.id.ivFlag);


        public MyViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }
        public void bind(CountryPojo countryPojo) {
            tvCountryName.setText(countryPojo.name);
            tvCapital.setText(countryPojo.capital);
            tvRegion.setText(countryPojo.getRegion());
            tvSubregion.setText(countryPojo.getSubregion());
            tvPopulation.setText(countryPojo.getPopulation().toString());
            tvBorder.setText(countryPojo.getBorders().toString());
            tvLang.setText(countryPojo.getLanguages().get(0).name);
            try{
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.generic_flag)
                        .error(R.mipmap.generic_flag)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH);

                Glide.with (context)
                        .load ( countryPojo.flag)
                        .apply(options)
                        .into (ivFlag);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
