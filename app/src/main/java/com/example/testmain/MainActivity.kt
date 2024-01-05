package com.example.testmain

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testmain.ui.theme.TestMainTheme
import java.util.TreeMap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestMainTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        val map = TreeMap<String,Int>()
        map["array_auto_parking"] = resources.getStringArray(R.array.array_auto_parking).size
        map["array_toast_super_park"] = resources.getStringArray(R.array.array_toast_super_park).size
        map["array_super_park_abnormal"] = resources.getStringArray(R.array.array_super_park_abnormal).size
        map["array_no_use_toast"] = resources.getStringArray(R.array.array_no_use_toast).size
        map["array_no_use"] = resources.getStringArray(R.array.array_no_use).size
        map["array_pro_parking"] = resources.getStringArray(R.array.array_pro_parking).size
        map["array_pro_parking_tts"] = resources.getStringArray(R.array.array_pro_parking_tts).size
        map["array_training_tips"] = resources.getStringArray(R.array.array_training_tips).size
        map["array_training_fail_reason"] = resources.getStringArray(R.array.array_training_fail_reason).size
        map["array_notification_words"] = resources.getStringArray(R.array.array_notification_words).size
        map["array_share_map_view_floors"] = resources.getStringArray(R.array.array_share_map_view_floors).size
        map["array_setting_dialog_tab"] = resources.getStringArray(R.array.array_setting_dialog_tab).size
        map.forEach { (t, u) ->
            Log.i("MainTest", "key $t == value $u")
        }
        Log.i("MainTest", "数组："+ resources.getStringArray(R.array.array_auto_parking)[0])
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestMainTheme {
        Greeting("Android")
    }
}