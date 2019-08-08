package com.sazib.pinboard.downloader

import android.app.ActivityManager
import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.LruCache
import android.widget.ImageView
import com.androidnetworking.widget.ANImageView
import com.sazib.pinboard.R
import com.sazib.pinboard.utils.logger.AppLogger
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

internal fun ANImageView.loadImage(url: String) {
  setDefaultImageResId(R.drawable.ic_loading)
  setErrorImageResId(R.drawable.ic_warning)
  setImageUrl(url)
}

internal fun ImageView.loadImage(
  url: String,
  errorRes: Int = R.drawable.ic_warning
) {
  ImageLoading(context).display(url, this, errorRes)
}

internal fun ImageView.loadResourceImage(
  resource: Int,
  errorRes: Int = R.drawable.ic_warning
) {

}

class ImageLoading(context: Context) : ComponentCallbacks2 {

  private val cache: TCLruCache

  init {
    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val maxKb = am.memoryClass * 1024
    val limitKb = maxKb / 8 // 1/8th of total ram
    cache = TCLruCache(limitKb)
  }

  fun display(
    url: String,
    imageview: ImageView,
    defaultresource: Int
  ) {
    imageview.setImageResource(defaultresource)
    val image = cache.get(url)
    if (image != null) {
      imageview.setImageBitmap(image)
    } else {
      SetImageTask(imageview).execute(url)
    }
  }

  override fun onConfigurationChanged(newConfig: Configuration) {}

  override fun onLowMemory() {}

  override fun onTrimMemory(level: Int) {
    if (level >= ComponentCallbacks2.TRIM_MEMORY_MODERATE) {
      cache.evictAll()
    } else if (level >= ComponentCallbacks2.TRIM_MEMORY_BACKGROUND) {
      cache.trimToSize(cache.size() / 2)
    }
  }

  private inner class TCLruCache(maxSize: Int) : LruCache<String, Bitmap>(maxSize) {

    override fun sizeOf(
      key: String,
      value: Bitmap
    ): Int {
      return value.byteCount / 1024
      //return super.sizeOf(key, value);
    }
  }

  private inner class SetImageTask(private val imageview: ImageView) : AsyncTask<String, Void, Int>() {
    private var bmp: Bitmap? = null

    override fun doInBackground(vararg params: String): Int? {
      val url = params[0]
      try {
        bmp = getBitmapFromURL(url)
        if (bmp != null) {
          cache.put(url, bmp)
        } else {
          return 0
        }
      } catch (e: Exception) {
        e.printStackTrace()
        return 0
      }

      return 1
    }

    override fun onPostExecute(result: Int?) {
      if (result == 1) {
        imageview.setImageBitmap(bmp)
      }
      super.onPostExecute(result)
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
      try {
        val url = URL(src)
        val connection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input = connection.inputStream
        return BitmapFactory.decodeStream(input)
      } catch (e: IOException) {
        AppLogger.d(e)
        return null
      }
    }
  }
}
