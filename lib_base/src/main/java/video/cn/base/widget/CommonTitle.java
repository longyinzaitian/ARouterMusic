package video.cn.base.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import video.cn.base.R;

/**
 * @author husy
 * @date 2019/9/1
 */
public class CommonTitle extends RelativeLayout {

    private Toolbar mToolbar;

    public CommonTitle(Context context) {
        super(context);
        addView(context, null);
    }

    public CommonTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(context, attrs);
    }

    public CommonTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView(context, attrs);
    }

    private void addView(Context context, AttributeSet attrs) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_common_title, this, false);
        mToolbar = root.findViewById(R.id.toolbar);
        addView(root);

        initView(context, attrs);
        setToolbar();
    }

    private void initView(Context context, AttributeSet attrs) {
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity)context).setSupportActionBar(mToolbar);
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CommonTitle);
        String title = array.getString(R.styleable.CommonTitle_title);
        setText(title);
        array.recycle();
    }

    public void setText(@StringRes int stringRes) {
        mToolbar.setTitle(stringRes);
    }

    public void setText(String title) {
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                mToolbar.setTitle(title);
            }
        });
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setToolbar() {
        mToolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity)getContext()).finish();
                }
            }
        });
    }

}
