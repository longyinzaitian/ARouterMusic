package video.cn.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.husy.network.model.Data;

import video.cn.base.utils.GlideUtil;
import video.cn.base.utils.LogUtil;
import video.cn.base.utils.TimeUtils;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/7
 */
public class HomeVideoAuthorView extends RelativeLayout {

    private ImageView imageView;
    private TextView nameTx;
    private TextView timeTx;
    private ImageView moreIm;

    public HomeVideoAuthorView(Context context) {
        super(context);
        initView(context);
    }

    public HomeVideoAuthorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeVideoAuthorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_video_author, this, false);
        addView(view);
        imageView = view.findViewById(R.id.home_author_avatar);
        nameTx = view.findViewById(R.id.home_author_name);
        timeTx = view.findViewById(R.id.home_author_time);
        moreIm = view.findViewById(R.id.home_author_more);
    }

    public void setInfo(final Data data) {
        GlideUtil.Companion.showCircleImage(getContext(), imageView, data.author.getIcon(), null);
        nameTx.setText(data.author.getName());
        timeTx.setText(TimeUtils.getTimeFormat(data.releaseTime));
        moreIm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.Companion.i("videoauthorview", "data:" + data);
            }
        });
    }

}
