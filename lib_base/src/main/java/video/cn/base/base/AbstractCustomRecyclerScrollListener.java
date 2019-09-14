package video.cn.base.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
            if (view.getContext() instanceof Activity) {
                Activity activity = (Activity) view.getContext();
                if (!activity.isFinishing()) {
                    Glide.get(view.getContext()).getRequestManagerRetriever().get(view.getContext()).resumeRequests();
                }
            }
        } else {
            Glide.get(view.getContext()).getRequestManagerRetriever().get(view.getContext()).pauseRequests();
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
        if (lastVisibleItem > linearLayoutManager.getItemCount() - 3) {
            onLoadMore();
        }

        onScroll(recyclerView, firstVisibleItem);
    }

    /**
     * load more
     */
    public abstract void onLoadMore();

    /**
     * on scroll
     * @param recyclerView recycle view
     * @param firstVisibleItem int
     */
    public abstract void onScroll(RecyclerView recyclerView, int firstVisibleItem);
}
