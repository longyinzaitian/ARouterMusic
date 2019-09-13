package com.husy.network.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.husy.network.R;
import com.husy.network.bingimage.LaunchResponse;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import video.cn.base.utils.GlideImageLoader;
import video.cn.base.utils.RouteUtils;

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
                ARouter.getInstance().build(RouteUtils.HOME_MAIN_IMAGE)
                        .withInt("pos", position)
                        .withParcelableArrayList("launchImages", (ArrayList<? extends Parcelable>) images)
                        .navigation();
            }
        });
    }

    public void setData(List<LaunchResponse.LaunchImage> images) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.clear();
        this.images.addAll(images);
        List<String> strings = new ArrayList<>();
        for (LaunchResponse.LaunchImage image : images) {
            strings.add(image.getUrl());
        }
        banner.setImages(strings).setImageLoader(new GlideImageLoader()).start();
    }


}
