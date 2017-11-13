package com.likelabs.pollsresult

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    fun addFragment(fragment: Fragment, fragmentTag: String) {
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rl_load_fragment, fragment, fragmentTag)
        fragmentTransaction.commit()
    }

    fun startingActivity(className: Class<*>, isFinishing: Boolean) {
        if (isFinishing) {
            finish()
        }
        startActivity(Intent(this, className))
    }
}
