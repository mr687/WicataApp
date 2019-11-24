package id.co.wicata.app.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.adapter.PreviewDestinationListAdapter;
import id.co.wicata.app.element.WrapContentHeightViewPager;

public class HistoryFragment extends Fragment {
    @BindView(R.id.carouselViewPager)
    WrapContentHeightViewPager carouselViewPager;

    private PreviewDestinationListAdapter destinationListAdapter;
    private TypedArray imgList;

    public HistoryFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgList = getActivity().getResources().obtainTypedArray(R.array.img_list);
        destinationListAdapter = new PreviewDestinationListAdapter(imgList, getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_regions, container, false);
        ButterKnife.bind(this,view);
        carouselViewPager.setAdapter(destinationListAdapter);
        carouselViewPager.setPadding(130,0,130,0);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
