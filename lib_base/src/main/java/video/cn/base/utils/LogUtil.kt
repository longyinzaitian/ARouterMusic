package video.cn.base.utils

import android.util.Log

/**
 *
 *
 * @author husy
 * @date 2019/3/17
 */
class LogUtil {
    companion object {
        fun i(tag: String, info: String) {
            Log.i(tag, info)
        }

        fun e(tag: String, info: String) {
            Log.e(tag, info)
        }
    }
}