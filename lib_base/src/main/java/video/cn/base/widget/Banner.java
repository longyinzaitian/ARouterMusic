package video.cn.base.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import video.cn.base.R;
import video.cn.base.utils.GlideUtil;

/**
 * @author husy
 * @date 2019/9/3
 */
public class Banner extends ViewPager {

    private List<String> images;

    public Banner(@NonNull Context context) {
        super(context);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageList(List<String> images) {
        this.images = images;
    }

    private class BannerAdapter extends FragmentPagerAdapter {

        private List<ImageFragment> imageFragments;
        public BannerAdapter(FragmentManager fm) {
            super(fm);
            imageFragments = new ArrayList<>();
            if (getCount() > 0) {
                for (String url : images) {
                    imageFragments.add(new ImageFragment());
                }
            }
        }

        @Override
        public int getCount() {
            return images == null ? 0 : images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return false;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Fragment getItem(int i) {
            return imageFragments.get(i);
        }
    }

    public static class ImageFragment extends Fragment {
        private static final String KEY = "url";
        private ImageView imageView;

        public ImageFragment() {
        }

        public static ImageFragment getInstance(String url) {
            ImageFragment imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putCharSequence(KEY, url);
            imageFragment.setArguments(bundle);
            return imageFragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.frm_image, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            imageView = view.findViewById(R.id.banner_image);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setData();
        }

        private void setData() {
            Bundle bundle = getArguments();
            if (bundle == null) {
                return;
            }

            String url = bundle.getString(KEY);
            GlideUtil.Companion.showImage(ImageFragment.this,
                    imageView, url, new GlideUtil.Companion.ImageListener() {
                        @Override
                        public void onFailed() {

                        }

                        @Override
                        public void onReady(@NotNull Drawable resource) {
                            imageView.setImageDrawable(resource);
                        }
                    });
        }
    }
}
