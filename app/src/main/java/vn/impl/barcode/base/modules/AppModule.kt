package vn.impl.barcode.base.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vn.impl.barcode.mainapp.fragments.forgotpassword.ForgotPassViewModel
import vn.impl.barcode.mainapp.fragments.login.LoginViewModel
import vn.impl.barcode.mainapp.fragments.scan.ResultViewModel
import vn.impl.barcode.mainapp.fragments.scan.ScanViewModel

val appModule = module {
    viewModel {
        ForgotPassViewModel()
        LoginViewModel()
        ScanViewModel()
        ResultViewModel()
    }

}