package com.live.shaadiassignment.utilities.helpers

import android.content.DialogInterface
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.live.shaadiassignment.R
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showAlert
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showAlertWithOptions
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showToast

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

abstract class BaseActivity : AppCompatActivity() {

    fun showToastMessage(message: String?) = showToast(this, message, Toast.LENGTH_SHORT)

    fun showSnackBar(parentView: View, message: String?) = ViewUtils.showSnackBar(parentView, message)

    fun showDialog(title: String?, message: String?, options: Boolean) {
        if (options) {
            showAlertWithOptions(
                this,
                title!!,
                message,
                getString(R.string.dialog_yes),
                getString(R.string.dialog_not_now),
                { dialog: DialogInterface, _: Int -> dialog.dismiss() },
                { dialog: DialogInterface, _: Int -> dialog.dismiss() },
                true
            )
        } else {
            showAlert(
                this,
                title!!,
                message,
                getString(R.string.dialog_ok),
                { dialog: DialogInterface, _: Int -> dialog.dismiss() },
                false
            )
        }
    }
}