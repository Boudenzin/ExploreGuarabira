package com.example.exploreguarabiraapp.data.repository

import android.graphics.Bitmap

interface ImageRepository {
    suspend fun getImage(url: String): Bitmap?
}