package video.cn.base.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 *
 *
 * @author husy
 * @date 2019/9/1
 */
class GlideUtil {
    companion object {
        fun showImage(activity: Activity, imageView: ImageView,
                      url: String,
                      imageListener: ImageListener?) {
            Glide.with(activity)
                    .load(url)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?,
                                                  target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            if (imageListener != null) {
                                imageListener.onFailed()
                            }
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?,
                                                     target: Target<Drawable>?, dataSource: DataSource?,
                                                     isFirstResource: Boolean): Boolean {
                            if (imageListener != null && resource != null) {
                                imageListener.onReady(resource)
                            }
                            imageView.setImageDrawable(resource)
                            return true
                        }
                    })
                    .into(imageView)
        }

        fun showImage(fragment: Fragment, imageView: ImageView,
                      url: String,
                      imageListener: ImageListener?) {
            Glide.with(fragment)
                    .load(url)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?,
                                                  target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            if (imageListener != null) {
                                imageListener.onFailed()
                            }
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?,
                                                     target: Target<Drawable>?, dataSource: DataSource?,
                                                     isFirstResource: Boolean): Boolean {
                            if (imageListener != null && resource != null) {
                                imageListener.onReady(resource)
                            }
                            imageView.setImageDrawable(resource)
                            return true
                        }
                    })
                    .into(imageView)
        }

        fun showCircleImage(context: Context, imageView: ImageView,
                      url:String, imageListener: ImageListener?) {
            Glide.with(context)
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?,
                                                  target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            if (imageListener != null) {
                                imageListener.onFailed()
                            }
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?,
                                                     target: Target<Drawable>?, dataSource: DataSource?,
                                                     isFirstResource: Boolean): Boolean {
                            if (imageListener != null && resource != null) {
                                imageListener.onReady(resource)
                            }
                            imageView.setImageDrawable(resource)
                            return true
                        }
                    })
                    .into(imageView)
        }

        interface ImageListener {
            fun onFailed()
            fun onReady(resource: Drawable)
        }
    }


}