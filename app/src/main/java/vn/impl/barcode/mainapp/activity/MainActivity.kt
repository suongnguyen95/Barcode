package vn.impl.barcode.mainapp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import vn.impl.barcode.R
import vn.impl.barcode.mainapp.fragments.scan.ScanFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addfragment(ScanFragment(), ScanFragment.TAG)
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

    fun showLoadding(isShow: Boolean) {
        loading_layout.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
    }
}