package video.cn.home.fragment.home;

import video.cn.base.base.BasePresenter;

/**
 * @author husy
 * @date 2019/9/2
 */
public class HomeFrPresenter extends BasePresenter<HomeContract.MainView>
        implements HomeContract.MainPresenter {

    public HomeFrPresenter(HomeContract.MainView iView) {
        super(iView);
    }

    @Override
    public void getHomeInfo() {


    }
}
