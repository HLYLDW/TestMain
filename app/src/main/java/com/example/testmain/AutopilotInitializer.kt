package com.example.testmain

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.startup.Initializer

class AutopilotInitializer: Initializer<Unit>{

    override fun create(context: Context) {
        Log.i("AutopilotInitializer" , "Initialize ：${context}")
        Handler(Looper.getMainLooper()).post {
            Log.i("AutopilotInitializer" , "post ：${context}")
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}