package video.cn.app.ui.launch;

import com.husy.network.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.husy.network.bingimage.LaunchImageRequest;
import com.husy.network.bingimage.LaunchResponse;
import video.cn.base.base.BasePresenter;
import video.cn.base.utils.LogUtil;

/**
 * @author husy
 * @date 2019/8/31
 */
public class LaunchPresenter extends BasePresenter<LaunchContract.LaunchView> implements LaunchContract.LaunchPresenter {

    public LaunchPresenter(LaunchContract.LaunchView iView) {
        super(iView);
    }

    @Override
    public void getImage() {
        RetrofitClient.getInstance().create(LaunchImageRequest.class)
            .getImage(1)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<LaunchResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(LaunchResponse launchResponse) {
                    LogUtil.Companion.i("response:", "" + launchResponse.toString());
                    if (iView == null || launchResponse.getImages() == null
                            || launchResponse.getImages().isEmpty()) {
                        return;
                    }

                    iView.showFlash("https://cn.bing.com" + launchResponse.getImages().get(0).getUrl());
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onComplete() {

                }
            });
    }

}
