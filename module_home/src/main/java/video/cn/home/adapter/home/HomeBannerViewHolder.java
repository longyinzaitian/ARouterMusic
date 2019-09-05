package video.cn.home.adapter.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.youth.banner.Banner;

import java.util.List;

import video.cn.home.R;
import video.cn.home.util.GlideImageLoader;

/**
 * @author husy
 * @date 2019/9/3
 */
public class HomeBannerViewHolder extends RecyclerView.ViewHolder {

    private Banner banner;

    public HomeBannerViewHolder(@NonNull View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.home_banner);
    }

    public void setData(List<String> images) {
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
    }


}
