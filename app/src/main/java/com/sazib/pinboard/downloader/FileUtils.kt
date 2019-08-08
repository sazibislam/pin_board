package com.sazib.pinboard.downloader

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.sazib.pinboard.BuildConfig
import com.sazib.pinboard.R
import com.sazib.pinboard.utils.logger.AppLogger
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

object FileUtils {
  /**
   * Get the Directory path
   * Note that here file saves as .pdf
   * **/
  fun getDerectoryInfo(context: Context): Pair<DirPath, FileName> {
    val folderName = "PINBOARD"
    val fileName =
      "${context.getString(R.string.app_name)}_${folderName}_${System.currentTimeMillis()}.pdf"
    val dirPath = when (isFileExist(context, folderName)) {
      true -> "${Environment.getExternalStorageDirectory()}//${BuildConfig.APPLICATION_ID}//$folderName"
      false -> "${Environment.getExternalStorageDirectory()}//${BuildConfig.APPLICATION_ID}//$folderName"
    }
    return Pair(DirPath(dirPath), FileName(fileName))
  }

  @Throws(IOException::class)
  fun loadJSONFromAsset(
    context: Context,
    jsonFileName: String
  ): String {
    (context.assets).open(jsonFileName)
        .let {
          val buffer = ByteArray(it.available())
          it.read(buffer)
          it.close()
          return String(buffer, Charset.forName("UTF-8"))
        }
  }

  /**
   * For browsing the the file from the storage
   * **/
  fun browseDocuments(appCompatActivity: AppCompatActivity) {
    val mimeTypes =
      arrayOf("application/msword", "text/plain", "application/pdf", "application/zip")

    val intent = Intent(Intent.ACTION_GET_CONTENT)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      intent.type = if (mimeTypes.size == 1) mimeTypes[0] else "*/*"
      if (mimeTypes.isNotEmpty()) {
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
      }
    } else {
      var mimeTypesStr = ""
      for (mimeType in mimeTypes) {
        mimeTypesStr += "$mimeType|"
      }
      intent.type = mimeTypesStr.substring(0, mimeTypesStr.length - 1)
    }
    appCompatActivity.startActivityForResult(Intent.createChooser(intent, "ChooseFile"), 100)
  }

  /**
   * Save image to the storage
   * **/
  fun saveImage(
    context: Context,
    scaledBitmap: Bitmap,
    imageName: String
  ) {
    try {
      val bytes = ByteArrayOutputStream()
      scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
      val dirPath =
        ("${Environment.getExternalStorageDirectory()}//${context.packageName}//$imageName")
      val file = File(dirPath)
      file.createNewFile()
      val fo = FileOutputStream(file)
      fo.write(bytes.toByteArray())
      fo.close()
    } catch (e: Exception) {
      AppLogger.d("copyImageToInternalStorage: " + e.message)
    }
  }

  /**
   * Get image from the storage
   * **/
  fun getImage(
    activity: Activity,
    imageName: String
  ): File? {
    if (isFileExist(activity, imageName)) {
      val dirPath =
        ("${Environment.getExternalStorageDirectory()}//${activity.packageName}//$imageName")
      return File(dirPath)
    }
    return null
  }

  /**
   * Checking if the file exists or not
   * **/
  fun isFileExist(
    context: Context,
    argName: String
  ): Boolean {
    isFolderExist(context)
    val dirPath = (Environment.getExternalStorageDirectory().toString()
        + "//"
        + context.packageName
        + "//"
        + argName)
    val f = File(dirPath)
    return f.exists()
  }

  /**
   * Checking if the path exists or not
   * **/
  private fun dirExists(directory_path: String): Boolean {
    var isExist = false
    val directory = File(directory_path)
    if (directory.exists() && directory.isDirectory) {
      isExist = true
    }
    return isExist
  }

  /**
   * Checking if the Folder exists or not
   * if the folder does not exist then it creates folder
   * **/
  private fun isFolderExist(context: Context): Boolean {
    val dirPath =
      Environment.getExternalStorageDirectory().toString() + "//" + context.packageName + "//"
    if (!dirExists(dirPath)) {
      val directory = File(dirPath)
      return directory.mkdirs()
    }
    return dirExists(dirPath)
  }

  /**
   * For deleting file from the storage
   * **/
  fun deleteFile(
    context: Context,
    argImageName: String
  ): Boolean {
    val dirPath = (Environment.getExternalStorageDirectory().toString()
        + "//"
        + context.packageName
        + "//"
        + argImageName)
    val file = File(dirPath)
    if (file.exists()) {
      file.delete()
    }
    return !file.exists()
  }
}

data class DirPath(val path: String)
data class FileName(val name: String)
