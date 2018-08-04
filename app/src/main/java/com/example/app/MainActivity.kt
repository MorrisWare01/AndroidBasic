package com.example.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.morrisware.android.basic.devsupport.DevSupportDialogFragment
import com.morrisware.android.basic.devsupport.DevSupportRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text.text = DevSupportRepository(application).getDevSupportSetting().apiUrl
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            DevSupportDialogFragment()
                .show(supportFragmentManager, "dev")
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

}
