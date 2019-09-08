package video.cn.home.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.husy.network.bingimage.LaunchResponse;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import video.cn.home.R;
import video.cn.home.act.ImageActivity;
import video.cn.home.util.GlideImageLoader;

/**
 * @author husy
 * @date 2019/9/3
 */
public class HomeBannerViewHolder extends RecyclerView.ViewHolder {

    private Banner banner;
    private ArrayList<LaunchResponse.LaunchImage> images;

    public HomeBannerViewHolder(final Context context, @NonNull View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.home_banner);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ImageActivity.launchActivity(context, images, position);
            }
        });
    }

    public void setData(List<LaunchResponse.LaunchImage> images) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.clear();
        this.images.addAll(images);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
    }


}
