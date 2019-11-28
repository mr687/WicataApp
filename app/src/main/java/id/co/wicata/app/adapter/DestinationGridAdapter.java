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
import id.co.wicata.app.Utils.App;
import id.co.wicata.app.activity.DetailDestinationActivity;
import id.co.wicata.app.model.Destination;

public class DestinationGridAdapter extends RecyclerView.Adapter<DestinationGridAdapter.ViewHolder> {
    private ArrayList<Destination> destinations;
    private Context context;
    public boolean showShummer = true;
    private int ITEM_SIZE = 4;

    public DestinationGridAdapter(ArrayList<Destination> destinations, Context context) {
        this.destinations = destinations;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_card_grid_destination, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(showShummer){
            holder.shimmer.startShimmer();
        } else {
            Destination destination = destinations.get(position);
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);

            holder.placeIcon.setVisibility(View.VISIBLE);
            holder.favoriteButton.setVisibility(View.VISIBLE);
            holder.tripButton.setVisibility(View.VISIBLE);

            holder.imageView.setBackground(null);
            Picasso.get().load(destination.getImageUrl()).into(holder.imageView);

            holder.titleView.setBackground(null);
            holder.titleView.setTextColor(context.getResources().getColor(R.color.colorBlack));
            holder.titleView.setText(destination.getTitle());

            holder.placeView.setBackground(null);
            holder.placeView.setText(destination.getLocationName());
            holder.placeView.setTextColor(context.getResources().getColor(R.color.colorTextLinkDark));

            holder.likeView.setBackground(null);
            holder.likeView.setTextColor(context.getResources().getColor(R.color.colorBlack));

            holder.cardView.setOnClickListener(v->{
                Intent intent = new Intent(context, DetailDestinationActivity.class);
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return showShummer ? ITEM_SIZE : destinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.titleView) TextView titleView;
        @BindView(R.id.shimmer) ShimmerFrameLayout shimmer;
        @BindView(R.id.likeView) TextView likeView;
        @BindView(R.id.placeView) TextView placeView;
        @BindView(R.id.placeIcon) ImageView placeIcon;
        @BindView(R.id.favoriteButton) ImageView favoriteButton;
        @BindView(R.id.tripButton) ImageView tripButton;
        @BindView(R.id.cardView) MaterialCardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
