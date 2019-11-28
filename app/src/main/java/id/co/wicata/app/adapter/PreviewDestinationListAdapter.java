package id.co.wicata.app.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;

public class PreviewDestinationListAdapter extends RecyclerView.Adapter<PreviewDestinationListAdapter.ViewHolder> {
    private int SHIMMER_ITEM_NUMBERS = 3;
    private TypedArray imgList;
    private Context context;
    public boolean showShimmer = true;

    public PreviewDestinationListAdapter(TypedArray imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_card_destination, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(showShimmer){
            holder.shimmer.startShimmer();
        }else{
            holder.shimmer.stopShimmer();
            holder.shimmer.setShimmer(null);

            holder.imageView.setBackground(null);
            holder.imageView.setImageResource(imgList.getResourceId(position,0));
        }
    }

    @Override
    public int getItemCount() {
        return showShimmer ? SHIMMER_ITEM_NUMBERS : imgList.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgView)
        ImageView imageView;
        @BindView(R.id.shimmer)
        ShimmerFrameLayout shimmer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
