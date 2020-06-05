package vn.impl.barcode.mainapp.fragments.scan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.scan_fragment.*
import vn.impl.barcode.R
import vn.impl.barcode.base.utils.QrCode
import vn.impl.barcode.mainapp.activity.CustomActivity
import kotlin.math.log

class ScanFragment : Fragment() {
    private var detector: BarcodeDetector? = null
    private var imageUri: Uri? = null
    private val REQUEST_WRITE_PERMISSION = 20

    companion object {
        fun newInstance() = ScanFragment()
        val TAG: String = ScanFragment::class.java.simpleName

    }

    private lateinit var viewModel: ScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scan_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScanViewModel::class.java)
        btn_scan.setOnClickListener {
            openCamera()

        }
    }



    private fun openCamera() {
        IntentIntegrator(activity).apply {
            captureActivity = CustomActivity::class.java // activity custom để thực hiện scan.
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            setPrompt("Đây là message thông báo hiển thị trên màn hình capture")
            setCameraId(0)
            setBeepEnabled(false)
            setOrientationLocked(false)
            initiateScan()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        result?.let {
            Log.d(TAG, "onActivityResult: ${result.contents}")
        }
    }
}
