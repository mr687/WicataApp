package id.co.wicata.app.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;

public class PreviewDestinationListAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private TypedArray imgList;
    private Context mContext;

    public PreviewDestinationListAdapter(TypedArray imgList, Context mContext) {
        this.imgList = imgList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imgList.length();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_card_destination, container, false);

        ImageView imageView = view.findViewById(R.id.imgView);
        imageView.setImageResource(imgList.getResourceId(position, 0));
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
