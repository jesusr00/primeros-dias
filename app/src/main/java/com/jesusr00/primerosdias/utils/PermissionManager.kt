package com.jesusr00.primerosdias.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager {
        companion object {
            fun isCallPermissionsGranted(mActivity: Activity): Boolean {
                return if (ContextCompat.checkSelfPermission(
                        mActivity,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        mActivity,
                        arrayOf(
                            Manifest.permission.CALL_PHONE
                        ), 100
                    )
                    false
                } else true
            }
        }
}