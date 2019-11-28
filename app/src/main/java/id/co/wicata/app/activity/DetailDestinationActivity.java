package id.co.wicata.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.Utils.Constants;

public class DetailDestinationActivity extends AppCompatActivity
implements Constants {
    @BindView(R.id.app_bar_detail)
    MaterialToolbar toolbar;
    @BindView(R.id.carouselView)
    CarouselView carouselView;

    String[] sampleImageUrl = {
        "https://i1.trekearth.com/photos/76487/magelang.jpg",
        "https://i1.trekearth.com/photos/34130/mandalawangi_gunung.jpg",
        "https://cdn.pixabay.com/photo/2013/10/25/15/42/tugu-pahlawan-200782_960_720.jpg",
        "https://3.bp.blogspot.com/-mR7QXUrN2fg/TdVb4I-wUyI/AAAAAAAAAJs/cQ1CnjZAr5k/s1600/bali-island-beach1.jpg",
        "https://3.bp.blogspot.com/_NFJ21nb2lMI/THeRuFc9tRI/AAAAAAAADWQ/hogO7TP78S4/s1600/The-Banten---Anyer---11-resize.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_destination);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v->finish());
        setTitle(null);

        carouselView.setPageCount(4);
        carouselView.setImageListener((position, imageView) -> {
            Picasso.get().load(sampleImageUrl[position]).into(imageView);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
