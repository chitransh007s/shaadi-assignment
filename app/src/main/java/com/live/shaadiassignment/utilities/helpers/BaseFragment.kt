package com.live.shaadiassignment.utilities.helpers

import android.app.ProgressDialog
import android.content.DialogInterface
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.live.shaadiassignment.R
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showAlert
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showAlertWithOptions
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showToast

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

abstract class BaseFragment : Fragment() {
    private var progressDialog: ProgressDialog? = null
    fun showHideProgress(progressBar: ProgressBar, visibility: Boolean) {
        if (visibility) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    fun showToastMessage(message: String?) {
        showToast(context, message, Toast.LENGTH_SHORT)
    }

    fun showDialog(title: String?, message: String?, options: Boolean) {
        if (options) {
            showAlertWithOptions(
                activity,
                title!!,
                message,
                getString(R.string.dialog_yes),
                getString(R.string.dialog_not_now),
                { dialog: DialogInterface, which: Int -> dialog.dismiss() },
                { dialog: DialogInterface, which: Int -> dialog.dismiss() },
                true
            )
        } else {
            showAlert(
                activity,
                title!!,
                message,
                getString(R.string.dialog_ok),
                { dialog: DialogInterface, which: Int -> dialog.dismiss() },
                false
            )
        }
    }

    fun showProgressDialog(title: String?, message: String?, show: Boolean) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(requireContext())
            progressDialog!!.isIndeterminate = false
            progressDialog!!.setCancelable(false)
        }
        if (show) {
            progressDialog!!.setTitle(title)
            progressDialog!!.setMessage(message)
            progressDialog!!.show()
        } else {
            progressDialog!!.dismiss()
        }
    }

}