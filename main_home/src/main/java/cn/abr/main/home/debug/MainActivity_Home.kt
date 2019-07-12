package cn.abr.main.home.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.abr.main.home.R
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/home/activity")
class MainActivity_Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_home_activity_main)
    }

}
