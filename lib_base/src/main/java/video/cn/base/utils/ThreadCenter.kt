package video.cn.base.utils

import android.app.Activity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *
 *
 * @author husy
 * @date 2019/3/13
 */
class ThreadCenter private constructor() {

    companion object {
        private var instance: ThreadCenter ? = null
            get() {
                if (field == null) {
                    field = ThreadCenter()
                }
                return field
            }

        fun getThreadCenter(): ThreadCenter {
            return instance!!
        }
    }

    private var threadPoolExecutor: ExecutorService ? = null
    init {
        threadPoolExecutor = Executors.newSingleThreadExecutor()
    }

    fun postUiRunnable(context: Activity, runnable: Runnable) {
        context.runOnUiThread(runnable)
    }

    fun postRunnable(runnable: Runnable) {
        threadPoolExecutor!!.execute(runnable)
    }

    fun stop() {
        threadPoolExecutor!!.shutdownNow()
    }
}