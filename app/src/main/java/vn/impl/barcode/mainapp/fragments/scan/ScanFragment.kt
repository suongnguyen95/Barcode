package vn.impl.barcode.mainapp.fragments.scan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.scan_fragment.*
import vn.impl.barcode.R
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class ScanFragment : Fragment() {
    private var detector: BarcodeDetector? = null
    private var imageUri: Uri? = null
    private val REQUEST_WRITE_PERMISSION = 20
    private val SAVED_INSTANCE_URI = "uri"
    internal var imageFile: File? = null
    private val SAVED_INSTANCE_RESULT = "result"
    private var currImagePath: String? = null
    private val PHOTO_REQUEST = 10


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
            //
            takePicture()
            detector = BarcodeDetector.Builder(activity?.applicationContext)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build()
//            if (!detector!!.isOperational) {
////                scanResults!!.text = "Could not set up the detector!"
//                return
//            }
        }

        // TODO: Use the ViewModel
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_WRITE_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            }

        }
    }

    private fun takePicture() {
        TODO("Not yet implemented")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        try {
            imageFile = createImageFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        var authorities: String = activity?.applicationContext?.packageName + ".fileprovider"



        imageUri = if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            Uri.fromFile(imageFile)
        } else {
            activity?.let { imageFile?.let { it1 ->
                FileProvider.getUriForFile(it, authorities,
                    it1
                )
            } }
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        if (activity?.packageManager?.let { intent.resolveActivity(it) } != null) {
            startActivityForResult(intent, PHOTO_REQUEST)
        }
    }
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir = File(Environment.getExternalStorageDirectory(), "picture.jpg")
        if (!storageDir.exists()) {
            storageDir.parentFile.mkdirs()
            storageDir.createNewFile()
        }
        currImagePath = storageDir.absolutePath
        return storageDir
    }


//    override fun onSaveInstanceState(outState: Bundle?) {
//        if (imageUri != null) {
//            outState!!.putString(SAVED_INSTANCE_URI, imageUri!!.toString())
////            outState.putString(SAVED_INSTANCE_RESULT, scanResults!!.text.toString())
//        }
//        super.onSaveInstanceState(outState!!)
//    }

    private fun launchMediaScanIntent(mediaScanIntent: Intent) {

        activity?.sendBroadcast(mediaScanIntent)
    }

    @Throws(FileNotFoundException::class)
    private fun decodeBitmapUri(ctx: Context, uri: Uri?): Bitmap? {

        val targetW = 600
        val targetH = 600
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true

        BitmapFactory.decodeStream(ctx.contentResolver.openInputStream(uri!!), null, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight

        val scaleFactor = Math.min(photoW / targetW, photoH / targetH)

        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor

        return BitmapFactory.decodeStream(ctx.contentResolver
            .openInputStream(uri!!), null, bmOptions)
    }

}