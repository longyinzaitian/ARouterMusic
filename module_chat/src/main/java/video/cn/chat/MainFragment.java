package video.cn.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import video.cn.base.base.BaseFragment;

/**
 * @author husyin
 * @date 2019年3月10日
 */
//@Route(path = RouteUtils.CHAT_FRAGMENT_MAIN)
public class MainFragment extends BaseFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weichat, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}