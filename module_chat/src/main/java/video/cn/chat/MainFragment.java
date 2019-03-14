package video.cn.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import video.cn.base.utils.RouteUtils;

/**
 * @author husyin
 * @date 2019年3月10日
 */
@Route(path = RouteUtils.CHAT_FRAGMENT_MAIN)
public class MainFragment extends Fragment {

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