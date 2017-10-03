package getprint.source.id.getprint.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import getprint.source.id.getprint.MainActivity;
import getprint.source.id.getprint.R;

/**
 * Created by Chandra on 11/2/2016.
 */
public class Tab_pengaturan extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] tabString = {
            "Atur",
            "Pengaturan",
            "Selesai"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_pengaturan);

        /*Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String data = (String) b.get("data");*/
        String data ="";
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager,data);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabStrigng();
    }
    private void setupViewPager(ViewPager viewPager, String data) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Pengaturan(data), "Atur");
        adapter.addFrag(new Pengaturan(data), "Pengaturan");
        adapter.addFrag(new Pengaturan(data), "Selesai");
        viewPager.setAdapter(adapter);
    }
    private void setupTabStrigng() {
        tabLayout.getTabAt(0).setText(tabString[0]);
        tabLayout.getTabAt(1).setText(tabString[1]);
        tabLayout.getTabAt(2).setText(tabString[2]);
    }
    public void onBackPressed(){
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Pengaturan fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }*/
        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }
    }
}
