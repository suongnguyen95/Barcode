package vn.impl.barcode.mainapp.fragments.forgotpassword

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.impl.barcode.R

class ForgotPassFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPassFragment()
    }

    private lateinit var viewModel: ForgotPassViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_pass_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ForgotPassViewModel::class.java)
        // TODO: Use the ViewModel
    }

}