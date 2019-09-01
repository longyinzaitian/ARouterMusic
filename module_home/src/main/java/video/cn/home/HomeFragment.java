package video.cn.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.base.BaseFragment;
import video.cn.base.utils.RouteUtils;
import video.cn.base.widget.CommonTitle;

/**
 * @author husy
 * @date 2019/9/1
 */
@Route(path = RouteUtils.HOME_FRAGMENT_MAIN)
public class HomeFragment extends BaseFragment {

    private CommonTitle mCommonTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCommonTitle = view.findViewById(R.id.home_common_title);
    }
}
