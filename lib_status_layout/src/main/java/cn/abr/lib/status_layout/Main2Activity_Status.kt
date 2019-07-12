package cn.abr.lib.status_layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * Fragment 中使用
 */
class Main2Activity_Status : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl, BlankFragment.newInstance()).commit()
    }
}
