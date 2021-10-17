package com.live.shaadiassignment.utilities.helpers

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.live.shaadiassignment.R

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

object ViewUtils {
    const val RIGHT_LEFT_STYLE = 1
    const val BOTTOM_UP_STYLE = 2
    const val FADE_IN_OUT_STYLE = 3

    fun showToast(context: Context?, message: String?) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    fun showToast(context: Context?, message: String?, toastLength: Int) {
        if (context != null) {
            Toast.makeText(context, message, toastLength).show()
        }
    }

    fun showSnackBar(parentView: View, message: String?) {
        if (parentView.isShown) {
            val snackBar = Snackbar.make(parentView, message!!, Snackbar.LENGTH_LONG)
            snackBar.show()
        }
    }

    fun showAlert(activity: Activity?, title: String, message: String?, positiveMessage: String?,
                  listener: DialogInterface.OnClickListener?, isCancellable: Boolean) {
        if (activity != null) {
            val alert = AlertDialog.Builder(activity)
            if (title != "") {
                alert.setTitle(title)
            }
            alert.setMessage(message)
            alert.setPositiveButton(positiveMessage, listener)
            alert.setCancelable(isCancellable)
            alert.show()
        }
    }

    fun showAlertWithOptions(activity: Activity?, title: String, message: String?, positiveMessage: String?,
                             negativeMessage: String?, positiveListener: DialogInterface.OnClickListener?,
                             negativeListener: DialogInterface.OnClickListener?, isCancellable: Boolean) {
        if (activity != null) {
            val alert = AlertDialog.Builder(activity)
            if (title != "") {
                alert.setTitle(title)
            }
            alert.setMessage(message)
            alert.setPositiveButton(positiveMessage, positiveListener)
            alert.setNegativeButton(negativeMessage, negativeListener)
            alert.setCancelable(isCancellable)
            alert.show()
        }
    }

    fun showPopup(activity: Activity?, parentView: View, layoutResource: Int, viewGroupResource: Int,
                  popupCallback: PopupCallback, style: Int, fullLayout: Boolean) {
        if (activity != null && !activity.isFinishing && parentView.isAttachedToWindow) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val viewGroup = parentView.findViewById<ViewGroup>(viewGroupResource)
            val popupView = inflater.inflate(layoutResource, viewGroup)
            val width = LinearLayout.LayoutParams.MATCH_PARENT
            var height = LinearLayout.LayoutParams.WRAP_CONTENT
            if (fullLayout) {
                height = LinearLayout.LayoutParams.MATCH_PARENT
            }
            val pw = PopupWindow(popupView, width, height, true)
            pw.isOutsideTouchable = true
            if (!fullLayout) {
                alphaPopupBackground(
                    parentView,
                    activity.baseContext
                )
                if (style == BOTTOM_UP_STYLE) {
                    pw.animationStyle = R.style.PopUpAnimation2
                } else {
                    pw.animationStyle = R.style.PopUpAnimation
                }
            } else {
                if (style == BOTTOM_UP_STYLE) {
                    pw.animationStyle = R.style.PopUpAnimationFullPage2
                } else if (style == FADE_IN_OUT_STYLE) {
                    pw.animationStyle = R.style.PopUpAnimationFullPage3
                } else {
                    pw.animationStyle = R.style.PopUpAnimationFullPage
                }
            }
            pw.setBackgroundDrawable(ColorDrawable(activity.resources.getColor(android.R.color.transparent)))
            pw.showAtLocation(parentView, Gravity.CENTER, 0, 0)
            pw.setOnDismissListener {
                unAlphaPopupBackground(
                    parentView,
                    activity.baseContext
                )
            }
            popupCallback.onClick(popupView, pw)
        }
    }

    fun showPopupWithAnchor(activity: Activity?, parentView: View, layoutResource: Int, viewGroupResource: Int,
                            popupCallback: PopupCallback, gravity: Int, fullLayout: Boolean) {
        if (activity != null && !activity.isFinishing && parentView.isAttachedToWindow) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val viewGroup = parentView.findViewById<ViewGroup>(viewGroupResource)
            val popupView = inflater.inflate(layoutResource, viewGroup)
            var width = LinearLayout.LayoutParams.WRAP_CONTENT
            if (fullLayout) {
                width = LinearLayout.LayoutParams.MATCH_PARENT
            }
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val pw = PopupWindow(popupView, width, height, true)
            pw.isOutsideTouchable = true
            pw.animationStyle = R.style.PopUpAnimation
            pw.setBackgroundDrawable(ColorDrawable(activity.resources.getColor(android.R.color.transparent)))
            popupView.measure(
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                ),
                View.MeasureSpec.makeMeasureSpec(
                    0,
                    View.MeasureSpec.UNSPECIFIED
                )
            )
            when (gravity) {
                Gravity.TOP -> {
                    popupView.measure(
                        View.MeasureSpec.makeMeasureSpec(
                            0,
                            View.MeasureSpec.UNSPECIFIED
                        ),
                        View.MeasureSpec.makeMeasureSpec(
                            0,
                            View.MeasureSpec.UNSPECIFIED
                        )
                    )
                    val originalPos = IntArray(2)
                    parentView.getLocationInWindow(originalPos)
                    val isOffScreen = true
                    var topOffsetValue = parentView.height + 10
                    if (isOffScreen) {
                        topOffsetValue += popupView.measuredHeight
                    }
                    pw.showAsDropDown(parentView, -10, -topOffsetValue, gravity)
                }
                Gravity.END -> {
                    val endOffsetValue =
                        parentView.height / 2 + popupView.measuredHeight / 2
                    pw.showAsDropDown(parentView, 15, -endOffsetValue, gravity)
                }
                Gravity.BOTTOM -> pw.showAsDropDown(parentView, 0, 30, gravity)
                Gravity.START -> pw.showAsDropDown(parentView, 0, 10, gravity)
                else -> pw.showAsDropDown(parentView)
            }
            popupCallback.onClick(popupView, pw)
        }
    }

    fun alphaPopupBackground(view: View, context: Context) {
        if (view.isShown) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                view.alpha = 0.5f
            } else {
                view.foreground = ContextCompat.getDrawable(context, R.drawable.alpha_bg)
            }
        }
    }

    fun unAlphaPopupBackground(view: View, context: Context) {
        if (view.isShown) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                view.alpha = 1.0f
            } else {
                view.foreground = ContextCompat.getDrawable(context, android.R.color.transparent)
            }
        }
    }

    interface PopupCallback {
        fun onClick(popupView: View, popupWindow: PopupWindow)
    }
}