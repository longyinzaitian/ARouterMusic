package video.cn.recommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.base.BaseFragment;
import video.cn.base.utils.RouteUtils;

/**
 * @author  husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.RECOM_FRAGMENT_MAIN)
public class MainFragment extends BaseFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recom, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}