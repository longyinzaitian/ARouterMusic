package video.cn.base.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @author husy
 * @date 2019/9/9
 */
public class ShareUtil {
    public static void share(Context context, String text, String title) {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(textIntent, title));
    }
}
