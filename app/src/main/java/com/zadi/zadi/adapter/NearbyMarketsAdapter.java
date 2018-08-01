package com.zadi.zadi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zadi.zadi.R;
import com.zadi.zadi.model.Nearby.NearbyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NearbyMarketsAdapter extends RecyclerView.Adapter<NearbyMarketsAdapter.MyViewHolder> {

    private List<NearbyData> nearbyMarketsModels;
    private onItemClicked onItemClicked;

    public NearbyMarketsAdapter(List<NearbyData> nearbyMarketsModels, onItemClicked onItemClicked) {
        this.nearbyMarketsModels = nearbyMarketsModels;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nearby_item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.supermarketName.setText(nearbyMarketsModels.get(position).getName());
        holder.deliverySpeed.setText(nearbyMarketsModels.get(position).getDeliverySpeed());
        holder.openingHours.setText(nearbyMarketsModels.get(position).getOpeningHour());
        if (!TextUtils.isEmpty(nearbyMarketsModels.get(position).getLogo()))
            Picasso.with(holder.marketLogo.getContext()).load(nearbyMarketsModels.get(position).getLogo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.onClicked(nearbyMarketsModels.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return nearbyMarketsModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.supermarket_name)
        TextView supermarketName;
        @BindView(R.id.opening_hours)
        TextView openingHours;
        @BindView(R.id.delivery_speed)
        TextView deliverySpeed;
        @BindView(R.id.market_logo)
        ImageView marketLogo;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface onItemClicked {
        void onClicked(NearbyData nearbyData);
    }
}
