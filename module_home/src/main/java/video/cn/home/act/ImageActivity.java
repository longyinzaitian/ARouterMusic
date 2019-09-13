package video.cn.home.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.husy.network.bingimage.LaunchResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import video.cn.base.base.BaseActivity;
import video.cn.base.utils.RouteUtils;
import video.cn.home.R;
import video.cn.home.adapter.image.ImagePagerAdapter;
import video.cn.home.fragment.image.ImageFragment;

/**
 * @author husy
 * @date 2019/9/8
 */
@Route(path = RouteUtils.HOME_MAIN_IMAGE)
public class ImageActivity extends BaseActivity {
    private static final String KEY = "launchImages";
    private static final String POSITION = "pos";

    private ViewPager viewPager;
    private TextView tvImageNum;

    private List<LaunchResponse.LaunchImage> launchImages;

    public static void launchActivity(Context context,
                                      ArrayList<LaunchResponse.LaunchImage> launchImages,
                                      int position) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putParcelableArrayListExtra(KEY, launchImages);
        intent.putExtra(POSITION, position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_list);

        tvImageNum = findViewById(R.id.act_image_num);
        viewPager = findViewById(R.id.act_image_view_pager);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        setData();
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            tvImageNum.setText(String.format(Locale.getDefault(),"%d/%d", i+1, launchImages.size()));
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private void setData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        this.launchImages = intent.getParcelableArrayListExtra(KEY);
        List<Fragment> fragments = new ArrayList<>();
        for (LaunchResponse.LaunchImage launchImage : launchImages) {
            fragments.add(ImageFragment.getInstance(launchImage));
        }

        int position = intent.getIntExtra(POSITION, 0);
        tvImageNum.setText(String.format(Locale.getDefault(),"%d/%d", position+1, launchImages.size()));
        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
    }
}
