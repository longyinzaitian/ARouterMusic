package video.cn.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 *
 * @author husyin
 * @date 2019年3月10日
 *
 */

public class LaunchActivity extends AppCompatActivity {

    private static final int SLEEP_TIME = 2000;
    private ImageView mSplashRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
    }

    private void initView() {
        mSplashRoot = findViewById(R.id.splash_root);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        mSplashRoot.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                long costTime = System.currentTimeMillis() - start;
                //wait
                if (SLEEP_TIME - costTime > 0) {
                    try {
                        Thread.sleep(SLEEP_TIME - costTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        }).start();

    }
}
