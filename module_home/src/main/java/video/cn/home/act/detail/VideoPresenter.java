package video.cn.home.act.detail;

import com.husy.network.AbstractCallListener;
import com.husy.network.home.Api;
import com.husy.network.model.Replies;

import java.net.URL;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;
import video.cn.base.utils.LogUtil;

/**
 * @author husy
 * @date 2019/9/10
 */
public class VideoPresenter extends BasePresenter<VideoDetailContract.VideoDetailView>
        implements VideoDetailContract.VideoPresenter {
    private long lastId;
    private boolean isNotMore = false;

    public VideoPresenter(VideoDetailContract.VideoDetailView iView) {
        super(iView);
    }

    @Override
    public void loadReplay(int id) {
        Api.getInstance().getReplies(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<Replies>() {
                    @Override
                    public void onResponse(Replies response) {
                        if (iView == null || response == null) {
                            return;
                        }

                        String next = response.getNextPageUrl();
                        try {
                            if (next == null) {
                                isNotMore = true;
                            } else {
                                URL url = new URL(next);
                                String query = url.getQuery();
                                String[] param = query.split("&");
                                for(String str : param) {
                                    if (str.indexOf("lastId") >= 0) {
                                        lastId = Integer.valueOf(str.split("=")[1]);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iView.setReplayData(response.replyList);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    private long lastIdRe = 0;

    @Override
    public void loadMoreReplay(int id) {
        if (isNotMore) {
            iView.addReplayData(null);
            return;
        }

        if (lastIdRe == lastId) {
            return;
        }
        lastIdRe = lastId;
        LogUtil.Companion.i("VideoPresenter", "lastId" + lastId);
        Api.getInstance().getReplies(id, (int)lastId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<Replies>() {
                    @Override
                    public void onResponse(Replies response) {
                        if (iView == null || response == null) {
                            return;
                        }

                        iView.addReplayData(response.replyList);
                        String next = response.getNextPageUrl();
                        try {
                            if (next == null) {
                                isNotMore = true;
                                return;
                            }
                            URL url = new URL(next);
                            String query = url.getQuery();
                            LogUtil.Companion.i("VideoPresenter", "query:" + query +", lastId:" + lastId);
                            String[] param = query.split("&");
                            for(String str : param) {
                                if (str.indexOf("lastId") >= 0) {
                                    lastId = Integer.valueOf(str.split("=")[1]);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
}
