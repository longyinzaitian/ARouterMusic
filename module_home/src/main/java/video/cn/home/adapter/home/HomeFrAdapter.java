package video.cn.home.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.husy.network.model.ItemList;

import java.util.ArrayList;
import java.util.List;

import video.cn.base.base.BaseAdapter;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/2
 *
 */
public class HomeFrAdapter extends BaseAdapter<ItemList> {
    public static final String VIDEO_TAG = "HomeFrAdapter_VIDEO_TAG";

    private static final String BANNER_TYPE = "banner2";
    private static final String VIDEO_TYPE = "video";
    private static final String TEXT_HEADER_TYPE = "textHeader";

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_VIDEO = 2;
    private static final int TYPE_TEXT_HEADER = 3;
    private List<String> images = new ArrayList<>();

    public HomeFrAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutIdByType(int type) {
        if (type == TYPE_BANNER) {
            return R.layout.adapter_layout_home_banner;
        } else if (type == TYPE_VIDEO) {
            return R.layout.adapter_layout_home_video;
        } else if (type == TYPE_TEXT_HEADER) {
            return R.layout.adapter_layout_home_text_header;
        }
        return super.getLayoutIdByType(type);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(int type, View view) {
        if (type == TYPE_BANNER) {
            return new HomeBannerViewHolder(view);
        } else if (type == TYPE_VIDEO) {
            return new HomeVideoViewHolder(context, view);
        } else if (type == TYPE_TEXT_HEADER) {
            return new HomeTextHeaderViewHolder(view);
        }
        return null;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos, ItemList itemList) {
        if (viewHolder instanceof HomeBannerViewHolder) {
            HomeBannerViewHolder homeBannerViewHolder = (HomeBannerViewHolder)viewHolder;
            homeBannerViewHolder.setData(images);

        } else if (viewHolder instanceof HomeVideoViewHolder) {
            HomeVideoViewHolder homeVideoViewHolder = (HomeVideoViewHolder)viewHolder;
            homeVideoViewHolder.setData(itemList.data);

        } else if (viewHolder instanceof HomeTextHeaderViewHolder) {
            HomeTextHeaderViewHolder textHeaderViewHolder = (HomeTextHeaderViewHolder) viewHolder;
            textHeaderViewHolder.setData(itemList.data);
        }
    }

    @Override
    public int getItemViewType(int position) {
        ItemList itemList = getItem(position);
        if (itemList == null) {
            return super.getItemViewType(position);
        }

        if (itemList.type.equals(BANNER_TYPE)) {
            return TYPE_BANNER;
        } else if (itemList.type.equals(VIDEO_TYPE)) {
            return TYPE_VIDEO;
        } else if (itemList.type.equals(TEXT_HEADER_TYPE)) {
            return TYPE_TEXT_HEADER;
        }
        return super.getItemViewType(position);
    }

    public void setBanner(List<String> images) {
        if (images == null || images.isEmpty()) {
            return;
        }

        this.images.clear();
        this.images.addAll(images);
        notifyDataSetChanged();
    }
}
