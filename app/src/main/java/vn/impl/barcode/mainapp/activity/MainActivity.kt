package vn.impl.barcode.mainapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import vn.impl.barcode.R
import vn.impl.barcode.base.utils.QrCode
import vn.impl.barcode.mainapp.fragments.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addfragment(LoginFragment(), LoginFragment.TAG)
        showLoadding(false)


    }

    fun addfragment(
        fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                fragment,
                fragmentTag
            ).commit()
    }

    fun repFragment(
        fragment: Fragment,
        fragmentTag: String
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment, fragmentTag
            )
            .commit()
    }

    fun showLoadding(isShow: Boolean) {
        loading_layout.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
    }
}