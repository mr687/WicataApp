package id.co.wicata.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.wicata.app.R;
import id.co.wicata.app.adapter.MainTabAdapter;
import id.co.wicata.app.adapter.PreviewDestinationListAdapter;
import id.co.wicata.app.element.CustomViewPager;

public class MainActivity extends BaseActivity {
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.search_click)
    EditText searchClick;
    @BindView(R.id.tabViewPager)
    CustomViewPager tabViewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    private MainTabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpToolbar();
        searchClick.setOnClickListener(v->{
            searchView.showSearch(false);
        });

        tabAdapter = new MainTabAdapter(getSupportFragmentManager(),0);
        tabViewPager.setAdapter(tabAdapter);
        tabViewPager.setPagingEnabled(false);
        tabs.setupWithViewPager(tabViewPager);
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.app_bar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle(null);
    }

    @Override
    public void onBackPressed() {
        if(searchView.isSearchOpen()){
            searchView.closeSearch();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.actionMessage);
        searchView.setMenuItem(item);
        return true;
    }
}
