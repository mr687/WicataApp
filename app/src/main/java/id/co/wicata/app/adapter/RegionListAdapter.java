package id.co.wicata.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.activity.DestinationListActivity;
import id.co.wicata.app.model.Region;

public class RegionListAdapter extends RecyclerView.Adapter<RegionListAdapter.ViewHolder> {
    private ArrayList<Region> regions;
    private Context context;
    public boolean showShimmer = true;
    private int SHIMMER_ITEM_NUMBERS = 3;

    public RegionListAdapter(ArrayList<Region> regions, Context context) {
        this.regions = regions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(this.context)
                .inflate(R.layout.item_card_region,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (showShimmer){

            holder.shimmer.startShimmer();

        }else{
            Region region = regions.get(position);

            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);

            holder.regionTitle.setBackground(null);
            holder.regionTitle.setText(region.getTitle());
            holder.regionTitle.setTextColor(context.getResources().getColor(R.color.colorTextWhite));

            holder.imageView.setBackground(null);
            holder.imageView.setAlpha(0.8f);
            Picasso.get().load(region.getBannerUrl()).into(holder.imageView);

            holder.cardView.setOnClickListener(v->{
                Intent intent = new Intent(context, DestinationListActivity.class);
                intent.putExtra(DestinationListActivity.TITLE_ACTIVITY_NAME, region.getTitle());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return showShimmer ? SHIMMER_ITEM_NUMBERS : regions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.regionTitle)
        TextView regionTitle;
        @BindView(R.id.shimmer)
        ShimmerFrameLayout shimmer;
        @BindView(R.id.cardView)
        MaterialCardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
