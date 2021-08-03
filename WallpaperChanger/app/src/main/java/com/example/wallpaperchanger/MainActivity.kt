package com.example.wallpaperchanger

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var myWallpaperList = arrayOf(R.drawable.wallpaper1,R.drawable.wallpaper2,R.drawable.wallpaper3,R.drawable.wallpaper4,R.drawable.wallpaper5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        change_wallpaper.setOnClickListener { setWallpaper() }
    }

    private fun setWallpaper() {
        Handler().postDelayed({
            for(i in myWallpaperList) {
                val pic: Bitmap = BitmapFactory.decodeResource(resources, i)
                val wM = WallpaperManager.getInstance(baseContext)
                wM.setBitmap(pic)
            }
        }, 3000)
    }
}