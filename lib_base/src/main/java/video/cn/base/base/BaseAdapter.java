package video.cn.base.base;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import video.cn.base.R;
import video.cn.base.utils.LogUtil;

/**
 * @author husy
 * @date 2019/9/7
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "BaseAdapter";
    private static final int TYPE_BOTTOM = 99;

    private BottomViewHolder bottomViewHolder;
    private ListListener<T> listListener;
    protected Context context;
    protected List<T> lists = new ArrayList<>();
    private boolean isNoMore = false;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return lists.size() == 0 ? 0 : lists.size() + 1;
    }

    @CallSuper
    @Override
    public int getItemViewType(int position) {
        if (lists == null || lists.isEmpty() || position < 0) {
            return super.getItemViewType(position);
        }

        if (position >= lists.size()-1) {
            return TYPE_BOTTOM;
        }
        return super.getItemViewType(position);
    }

    @CallSuper
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        int layoutId = getLayoutIdByType(type);
        LogUtil.Companion.i(TAG, "on create view holder. type:"
                + type + ", layout id:" +layoutId + ", type:" + type);

        View view = null;
        if (type > 0) {
            view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
            if (type == TYPE_BOTTOM) {
                return new BottomViewHolder(view);
            }
        }
        return onCreateViewHolder(type, view);
    }

    /**
     * override
     * @param type int
     * @param view view
     * @return RecyclerView.ViewHolder
     */
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(int type, View view);

    protected T getItem(int position) {
        if (position >= lists.size() || position < 0) {
            return null;
        }

        return lists.get(position);
    }

    @CallSuper
    protected int getLayoutIdByType(int type) {
        if (type == TYPE_BOTTOM) {
            return R.layout.adapter_bottom_view;
        }
        return -1;
    }

    @CallSuper
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof BottomViewHolder) {
            bottomViewHolder = (BottomViewHolder)viewHolder;
            setBottomView();
            return;
        }

        onBindViewHolder(viewHolder, i, getItem(viewHolder.getAdapterPosition()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.Companion.i(TAG, "item click. T:" + getItem(viewHolder.getAdapterPosition()));
                if (listListener != null) {
                    listListener.onItemClick(getItem(viewHolder.getAdapterPosition()));
                }
            }
        });

    }

    /**
     * bind view
     * @param viewHolder view holder
     * @param pos int
     * @param t T
     */
    protected abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int pos, T t);

    /**
     * not more data
     */
    protected void notMoreData() {
        if (bottomViewHolder != null) {
            bottomViewHolder.notMoreData();
        }
        isNoMore = true;
    }

    public void setData(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        lists.clear();
        lists.addAll(list);
        notifyItemRangeInserted(0, lists.size());
    }

    public void addData(List<T> list) {
        if (list == null || list.isEmpty()) {
            notMoreData();
            return;
        }

        lists.addAll(list);
        notifyItemRangeInserted(lists.size(), list.size());
    }

    public void setListListener(ListListener<T> listListener) {
        this.listListener = listListener;
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {

        private View bottomView;
        private TextView textView;

        public BottomViewHolder(@NonNull View itemView) {
            super(itemView);
            bottomView = itemView;
            textView = itemView.findViewById(R.id.adapter_bottom_text_view);
        }

        public void notMoreData() {
            textView.setText("没有更多...");
        }
    }

    private void setBottomView() {
        if (bottomViewHolder == null) {
            return;
        }

        if (isNoMore) {
            bottomViewHolder.notMoreData();
        }

        bottomViewHolder.bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.Companion.i(TAG, "load more click. ");
                if (listListener != null) {
                    listListener.onClickLoadMore();
                }
            }
        });
    }

    public interface ListListener<T> {
        /**
         * load more
         */
        void onClickLoadMore();

        /**
         * click item
         * @param t T
         */
        void onItemClick(T t);
    }

}
