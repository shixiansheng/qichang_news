package cn.abr.main.home.debug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.abr.main.home.R
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = "/home/activity")
class MainActivity_Home : AppCompatActivity() {

    @JvmField
    @Autowired(name = "a")
    var a: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().inject(this)
        Toast.makeText(this,a,0).show()
    }
}
