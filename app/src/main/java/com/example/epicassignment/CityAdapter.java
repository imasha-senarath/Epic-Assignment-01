package com.example.epicassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<CityModel> mCityModelList;
    private Context mContext;
    private String searchKeyword;

    public CityAdapter(Context context, List<CityModel> cityModelList, String searchKeyword)
    {
        this.mContext = context;
        this.mCityModelList = cityModelList;
        this.searchKeyword = searchKeyword;
    }

    @NotNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new CityAdapter.CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_city,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CityViewHolder holder, int position) {

        CityModel cityModel = mCityModelList.get(position);
        String title = cityModel.getTitle();
        String woeid = cityModel.getWoeid();
        String lattLong = cityModel.getLatt_long();

        if(!searchKeyword.isEmpty()) {
            if(title.toLowerCase().contains(searchKeyword.toLowerCase())) {
                holder.title.setText(title);
                holder.woeid.setText("WOEID: "+woeid);
                holder.lattLang.setText("Latt Long: "+lattLong);
            } else {
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        } else {
            holder.title.setText(title);
            holder.woeid.setText("WOEID: "+woeid);
            holder.lattLang.setText("Latt Long: "+lattLong);
        }

    }

    @Override
    public int getItemCount() {
        return mCityModelList.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView title, woeid, lattLang;

        public CityViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.row_city_title);
            woeid = itemView.findViewById(R.id.row_city_woeid);
            lattLang = itemView.findViewById(R.id.row_city_latt_long);
        }
    }
}
