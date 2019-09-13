package video.cn.recommend.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.husy.network.model.ItemList;

import video.cn.base.base.BaseAdapter;
import video.cn.base.utils.GlideUtil;
import video.cn.recommend.R;

/**
 * @author husy
 * @date 2019/9/13
 */
public class MainAdapter extends BaseAdapter<ItemList> {

    public MainAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    protected int getLayoutIdByType(int type) {
        if (type == 1) {
            return R.layout.adapter_frm_recom;
        }
        return super.getLayoutIdByType(type);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(int type, View view) {
        if (view != null) {
            return new Holder(context, view);
        }
        return null;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos, ItemList itemList) {
        if (viewHolder instanceof Holder) {
            Holder holder = (Holder) viewHolder;
            holder.setData(itemList);
        }
    }

    private static class Holder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView text;
        private Context context;

        public Holder(Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            image = itemView.findViewById(R.id.adapter_image);
            text = itemView.findViewById(R.id.adapter_text);
        }

        public void setData(ItemList itemList) {
            text.setText(itemList.data.title);
            GlideUtil.Companion.showImage(((Activity)context), image, itemList.data.image, null);
        }

    }
}
