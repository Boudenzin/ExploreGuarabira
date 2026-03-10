package com.example.exploreguarabiraapp.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL

class ImageRepositoryImpl(
    private val context: Context
) : ImageRepository {

    private val memoryCache = LruCache<String, Bitmap>(20)

    override suspend fun getImage(url: String): Bitmap? = withContext(Dispatchers.IO){
        val fileName = url.hashCode().toString()

        memoryCache.get(fileName)?.let {
            return@withContext it
        }

        val file = File(context.cacheDir, fileName)
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            if (bitmap != null) {
                memoryCache.put(fileName, bitmap)
                return@withContext bitmap
            }
        }

        return@withContext try {
            val connection = URL(url).openConnection()
            connection.connect()

            val input = connection.getInputStream()
            file.outputStream().use { output ->
                input.copyTo(output)
            }

            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            bitmap?.also {
                memoryCache.put(fileName, it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}