package vn.impl.barcode.mainapp.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.login_fragment.*
import vn.impl.barcode.R
import vn.impl.barcode.mainapp.activity.MainActivity
import vn.impl.barcode.mainapp.fragments.forgotpassword.ForgotPassFragment
import vn.impl.barcode.mainapp.fragments.scan.ScanFragment

class LoginFragment : Fragment() {
    var mainActivity: MainActivity? = null

    companion object {
        val TAG: String = LoginFragment::class.java.simpleName
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        mainActivity = activity as MainActivity
        btn_login.setOnClickListener {
            mainActivity?.repFragment(ScanFragment(), ScanFragment.TAG)
        }
        btn_resetpass.setOnClickListener {
            mainActivity?.repFragment(ForgotPassFragment(), ForgotPassFragment.TAG)
        }
    }

}