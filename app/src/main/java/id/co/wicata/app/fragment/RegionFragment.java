package id.co.wicata.app.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.adapter.PreviewDestinationListAdapter;
import id.co.wicata.app.adapter.RegionListAdapter;
import id.co.wicata.app.element.CustomNestedScrollView;
import id.co.wicata.app.element.WrapContentHeightViewPager;
import id.co.wicata.app.model.Region;

public class RegionFragment extends Fragment {
    @BindView(R.id.carouselViewPager)
    ViewPager2 carouselViewPager;
    @BindView(R.id.regionRecyclerView)
    RecyclerView regionRecyclerView;
    @BindView(R.id.hero)
    ImageView hero;
    @BindView(R.id.scroolView)
    CustomNestedScrollView scrollView;

    private PreviewDestinationListAdapter destinationListAdapter;
    private TypedArray imgList;
    private RegionListAdapter regionListAdapter;
    private ArrayList<Region> regions = new ArrayList<>();

    public RegionFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void loadDummy(){
        regions.add(new Region(0,"Jawa Tengah","https://i1.trekearth.com/photos/76487/magelang.jpg"));
        regions.add(new Region(1,"Jawa Barat","https://i1.trekearth.com/photos/34130/mandalawangi_gunung.jpg"));
        regions.add(new Region(2,"Jawa Timur","https://cdn.pixabay.com/photo/2013/10/25/15/42/tugu-pahlawan-200782_960_720.jpg"));
        regions.add(new Region(3,"Bali","https://3.bp.blogspot.com/-mR7QXUrN2fg/TdVb4I-wUyI/AAAAAAAAAJs/cQ1CnjZAr5k/s1600/bali-island-beach1.jpg"));
        regions.add(new Region(4,"Banten","https://3.bp.blogspot.com/_NFJ21nb2lMI/THeRuFc9tRI/AAAAAAAADWQ/hogO7TP78S4/s1600/The-Banten---Anyer---11-resize.jpg"));
    }

    private void setRegionAdapter() {
        regionListAdapter = new RegionListAdapter(regions,getActivity());
        regionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        regionRecyclerView.setHasFixedSize(true);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        regionRecyclerView.setAdapter(regionListAdapter);
    }

    private void setPreviewAdapter(){
        destinationListAdapter = new PreviewDestinationListAdapter(imgList, getActivity());
        carouselViewPager.setAdapter(destinationListAdapter);
        carouselViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        carouselViewPager.setOffscreenPageLimit(3);

        float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        carouselViewPager.setPageTransformer((page, position) -> {
            float myOffset = position * -(2 * pageOffset + pageMargin);
            if (position < -1) {
                page.setTranslationX(-myOffset);
            } else if (position <= 1) {
                float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                page.setTranslationX(myOffset);
                page.setScaleY(scaleFactor);
                page.setAlpha(scaleFactor);
            } else {
                page.setAlpha(0);
                page.setTranslationX(myOffset);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_regions, container, false);
        ButterKnife.bind(this,view);

        imgList = getActivity().getResources().obtainTypedArray(R.array.img_list);

        setRegionAdapter();

        setPreviewAdapter();

        new Handler().postDelayed(() -> {
            loadDummy();
            regionListAdapter.showShimmer = false;
            regionListAdapter.notifyDataSetChanged();

            imgList = getActivity().getResources().obtainTypedArray(R.array.img_list);
            destinationListAdapter.showShimmer = false;
            destinationListAdapter.notifyDataSetChanged();
        }, 5000);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
