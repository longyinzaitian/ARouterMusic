package video.cn.base.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author husy
 * @date 2019/9/7
 */
public abstract class AbstractCustomRecyclerScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView view, int scrollState) {
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            LinearLayoutManager linearLayoutManager = ((LinearLayoutManager)view.getLayoutManager());
            if (linearLayoutManager == null) {
                return;
            }

            int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            if (lastVisibleItem > linearLayoutManager.getItemCount() - 3) {
                onLoadMore();
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager linearLayoutManager = ((LinearLayoutManager)recyclerView.getLayoutManager());
        if (linearLayoutManager == null) {
            return;
        }
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

    }

    public abstract void onLoadMore();
}
