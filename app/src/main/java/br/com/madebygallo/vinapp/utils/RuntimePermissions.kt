package br.com.madebygallo.vinapp.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.madebygallo.vinapp.R
import br.com.madebygallo.vinapp.utils.ConstantsUtil.CAMERA_PERMISSION
import br.com.madebygallo.vinapp.utils.ConstantsUtil.CAMERA_READ_WRITE_PERMISSION
import br.com.madebygallo.vinapp.utils.ConstantsUtil.READ_EXTERNAL_STORAGE
import br.com.madebygallo.vinapp.utils.ConstantsUtil.WRITE_EXTERNAL_STORAGE
import java.util.ArrayList

/**
 * Created by RaqG on 15/07/2020.
 */

object RunTimePermission {

    fun checkAndRequestPermissions(activity: Activity): Boolean {
//        val cameraPermission = ContextCompat.checkSelfPermission(activity,Manifest.permission.CAMERA)
        val writePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
//        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA)
//        }
        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (readPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toTypedArray(), CAMERA_READ_WRITE_PERMISSION)
            return false
        }
        return true
    }

    fun checkPermission(activity: Activity, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }

    fun callCameraDialog(activity: Activity) {
        val text = activity.resources.getString(R.string.camera_permission_denied_message)
        callDialog(activity, text, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION)
    }

    fun callWriteExternalStorageDialog(activity: Activity) {
        val text = activity.resources.getString(R.string.write_external_storage_permission_denied_message)
        callDialog(activity, text, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), WRITE_EXTERNAL_STORAGE)
    }

    fun checkReadExternalStoragePermission(activity: Activity): Boolean {
        if (!isReadExternalStoragePermissionGranted(activity)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                callReadExternalStorageDialog(activity)
            } else {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE)
            }
        } else {
            return true
        }
        return false
    }

    private fun isReadExternalStoragePermissionGranted(activity: Activity): Boolean {
        return ActivityCompat.checkSelfPermission(activity,
            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    fun callReadExternalStorageDialog(activity: Activity) {
        val text = activity.resources.getString(R.string.read_external_storage_permission_denied_message)
        callDialog(activity, text, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE)
    }

    private fun callDialog(activity: Activity, message: String, permissions: Array<String>, request_code: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.permission_alert_title)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.dialog_button_allow) { dialog, _ ->
            ActivityCompat.requestPermissions(activity, permissions, request_code)
            dialog.dismiss()
        }
        builder.setNegativeButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }
}