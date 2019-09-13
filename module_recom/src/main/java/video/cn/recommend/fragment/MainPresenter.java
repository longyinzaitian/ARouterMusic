package video.cn.recommend.fragment;

import com.husy.network.AbstractCallListener;
import com.husy.network.RetrofitClient;
import com.husy.network.bingimage.LaunchImageRequest;
import com.husy.network.bingimage.LaunchResponse;
import com.husy.network.home.Api;
import com.husy.network.model.Discover;
import com.husy.network.model.ItemList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import video.cn.base.base.BasePresenter;

/**
 * @author husy
 * @date 2019/9/13
 */
public class MainPresenter extends BasePresenter<MainContract.MainView>
        implements MainContract.MainPresenter {

    public MainPresenter(MainContract.MainView iView) {
        super(iView);
    }

    @Override
    public void loadData() {
        Api.getInstance().getDiscover()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new AbstractCallListener<Discover>() {
                @Override
                public void onResponse(Discover response) {
                    if (iView == null || response == null) {
                        return;
                    }

                    List<ItemList> list = response.getItemList();
                    List<ItemList> data = new ArrayList<>();
                    for (ItemList itemList : list) {
                        if (itemList.type.equals("squareCard")
                            && itemList.data.id > 0) {
                            data.add(itemList);
                        }
                    }

                    iView.setData(data);
                }

                @Override
                public void onFail(Throwable e) {

                }
            });

        loadBanner();
    }

    private void loadBanner() {
        RetrofitClient.getInstance().create(LaunchImageRequest.class)
                .getImage(10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbstractCallListener<LaunchResponse>() {
                    @Override
                    public void onResponse(LaunchResponse launchResponse) {
                        if (iView == null || launchResponse.getImages() == null
                                || launchResponse.getImages().isEmpty()) {
                            return;
                        }

                        List<LaunchResponse.LaunchImage> images = launchResponse.getImages();
                        images.remove(0);
                        images.remove(0);
                        images.remove(0);
                        images.remove(0);
                        images.remove(0);
                        List<String> strings = new ArrayList<>();
                        for (LaunchResponse.LaunchImage image : images) {
                            strings.add(image.getUrl());
                        }
                        iView.setBanner(strings, images);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

}
