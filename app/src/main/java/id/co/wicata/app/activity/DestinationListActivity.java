package id.co.wicata.app.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.adapter.DestinationGridAdapter;
import id.co.wicata.app.model.Destination;

public class DestinationListActivity extends BaseActivity {
    @BindView(R.id.destinationRecyclerView)
    RecyclerView destinationRecyclerView;

    public final static String TITLE_ACTIVITY_NAME = "title";

    private DestinationGridAdapter adapter;
    private ArrayList<Destination> destinations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_list);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra(TITLE_ACTIVITY_NAME);
        getSupportActionBar().setTitle(title.trim());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        adapter = new DestinationGridAdapter(destinations, this);
        destinationRecyclerView.setItemAnimator(new DefaultItemAnimator());
        destinationRecyclerView.setAdapter(adapter);
        destinationRecyclerView.setHasFixedSize(true);
        destinationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Handler().postDelayed(() -> {
            dummyData();
            adapter.showShummer = false;
            adapter.notifyDataSetChanged();
        },5000);
    }

    private void dummyData(){
        destinations.add(new Destination(0,"https://i1.trekearth.com/photos/76487/magelang.jpg", "Pantai Kuta", "Bali, Indonesia"));
        destinations.add(new Destination(1,"https://i1.trekearth.com/photos/34130/mandalawangi_gunung.jpg", "Pantai Kartini", "Jepara, Indonesia"));
        destinations.add(new Destination(2,"https://cdn.pixabay.com/photo/2013/10/25/15/42/tugu-pahlawan-200782_960_720.jpg", "Pantai Wates", "Rembang, Indonesia"));
        destinations.add(new Destination(3,"https://3.bp.blogspot.com/-mR7QXUrN2fg/TdVb4I-wUyI/AAAAAAAAAJs/cQ1CnjZAr5k/s1600/bali-island-beach1.jpg", "Pantai Indrayanti", "Yogyakarta, Indonesia"));
        destinations.add(new Destination(4,"https://3.bp.blogspot.com/_NFJ21nb2lMI/THeRuFc9tRI/AAAAAAAADWQ/hogO7TP78S4/s1600/The-Banten---Anyer---11-resize.jpg", "Pantai Wonosari", "Pati, Indonesia"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

