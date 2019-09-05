package video.cn.home.adapter.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.husy.network.model.ItemList;

import java.util.List;

/**
 * @author husy
 * @date 2019/9/2
 *
 */
public class HomeFrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String BANNER_TYPE = "banner2";
    private static final String VIDEO = "video";

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_VIDEO = 2;

    private List<ItemList> itemList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        ItemList itemList = this.itemList.get(position);
        if (itemList.type.equals(BANNER_TYPE)) {
            return TYPE_BANNER;
        } else if (itemList.type.equals(VIDEO)) {
            return TYPE_VIDEO;
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public void setData(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    public void addData(List<ItemList> itemList) {
        this.itemList.addAll(itemList);
    }
}
