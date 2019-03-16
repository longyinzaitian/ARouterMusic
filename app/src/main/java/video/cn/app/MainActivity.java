package video.cn.app;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import video.cn.app.utils.FragmentUtils;
import video.cn.base.base.BaseActivity;

/**
 *
 * @author husyin
 * @date 2019年3月10日
 *
 */
public class MainActivity extends BaseActivity {

    private ViewPager mMViewPager;
    private TabLayout mToolbarTab;
    /**
     * 图标
     */
    private int[] tabIcons = {
            R.drawable.tab_home,
            R.drawable.tab_weichat,
            R.drawable.tab_recommend,
            R.drawable.tab_user
    };
    private String[] tabArray;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        // 给viewpager设置适配器
        setViewPagerAdapter();
        setTabBindViewPager();
        setItem();
    }

    private void initData() {
        tabArray = getResources().getStringArray(R.array.tab_main);
        fragments.clear();
        fragments.add(FragmentUtils.getHomeFragment());
        fragments.add(FragmentUtils.getChatFragment());
        fragments.add(FragmentUtils.getRecomFragment());
        fragments.add(FragmentUtils.getMeFragment());
    }

    private void initView() {
        mMViewPager = findViewById(R.id.mViewPager);
        mToolbarTab = findViewById(R.id.toolbar_tab);

    }

    private void setViewPagerAdapter() {
        DemandAdapter mDemandAdapter = new DemandAdapter(getSupportFragmentManager());
        mMViewPager.setAdapter(mDemandAdapter);
    }

    private void setTabBindViewPager() {
        mToolbarTab.setupWithViewPager(mMViewPager);
    }

    private void setItem() {
        /*
         * 一定要在设置适配器之后设置Icon
         */
        for (int i = 0; i < mToolbarTab.getTabCount(); i++) {
            TabLayout.Tab tab = mToolbarTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null, false);
        ImageView tabImage = view.findViewById(R.id.tab_image);
        TextView tabText = view.findViewById(R.id.tab_text);
        tabImage.setImageResource(tabIcons[position]);
        tabText.setText(tabArray[position]);
        return view;
    }

    /**
     * 适配器
     */
    public class DemandAdapter extends FragmentStatePagerAdapter {

        DemandAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}
