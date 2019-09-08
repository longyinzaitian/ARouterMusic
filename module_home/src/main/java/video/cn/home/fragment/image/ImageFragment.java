package video.cn.home.fragment.image;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.husy.network.bingimage.LaunchResponse;

import video.cn.base.base.BaseFragment;
import video.cn.base.utils.GlideUtil;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/8
 */
public class ImageFragment extends BaseFragment {
    private static final String IMAGE = "image";

    private ImageView imageView;
    private TextView imageTitle;

    public static Fragment getInstance(LaunchResponse.LaunchImage launchImage) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IMAGE, launchImage);
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.frm_image);
        imageTitle = view.findViewById(R.id.frm_image_title);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity == null || activity.isFinishing()) {
                    return;
                }
                activity.finish();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
    }

    private void setData() {
        if (getArguments() == null) {
            return;
        }

        LaunchResponse.LaunchImage launchImage = getArguments().getParcelable(IMAGE);

        if (launchImage.getCopyright() != null) {
            imageTitle.setText(launchImage.getCopyright());
        }

        GlideUtil.Companion.showImage(this, imageView, launchImage.getUrl(), null);
    }

}
