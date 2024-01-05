package com.example.testmain

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import kotlin.system.exitProcess

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("AutopilotInitializer" , "Application1:$this")
//        android.os.Process.killProcess(android.os.Process.myPid());
//        exitProcess(10)
//        Log.i("AutopilotInitializer" , "Application2:$this")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("AutopilotInitializer" , "onConfigurationChanged:$this")
    }
}