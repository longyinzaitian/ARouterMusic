package video.cn.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.husy.network.model.Data;

import video.cn.base.utils.LogUtil;
import video.cn.home.R;

/**
 * @author husy
 * @date 2019/9/8
 */
public class HomeVideoMenuView extends RelativeLayout {

    private LinearLayout collectLv;
    private TextView collectTx;
    private LinearLayout replyLv;
    private TextView replyTx;
    private LinearLayout shareLv;
    private TextView shareTx;

    private Data data;

    public HomeVideoMenuView(Context context) {
        super(context);
        initView(context);
    }

    public HomeVideoMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomeVideoMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_video_menu, this, false);
        addView(view);
        collectLv = view.findViewById(R.id.home_video_collect);
        collectTx = view.findViewById(R.id.home_video_collect_text);
        replyLv = view.findViewById(R.id.home_video_reply);
        replyTx = view.findViewById(R.id.home_video_reply_text);
        shareLv = view.findViewById(R.id.home_video_share);
        shareTx = view.findViewById(R.id.home_video_share_text);

        collectLv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.Companion.i("home video menu. collect:", "data:" + data);
            }
        });

        replyLv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.Companion.i("home video menu. reply:", "data:" + data);
            }
        });

        shareLv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.Companion.i("home video menu. share:", "data:" + data);
            }
        });
    }

    public void setData(Data data) {
        this.data = data;
        collectTx.setText(String.valueOf(data.consumption.collectionCount));
        replyTx.setText(String.valueOf(data.consumption.replyCount));
        shareTx.setText(String.valueOf(data.consumption.shareCount));
    }
}
