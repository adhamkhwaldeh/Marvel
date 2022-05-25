package com.aljawad.sons.mainlibrary.base

import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialogFragment : DialogFragment() {

    abstract var baseDialogTag: String

    companion object {
        fun closeDialog(supportFragmentManager: FragmentManager, dialogTag: String) {
            try {
                val dialog: BaseDialogFragment? = supportFragmentManager
                    .findFragmentByTag(dialogTag) as BaseDialogFragment?
                val res = dialog?.dialog?.isShowing
                if ((res != null) && (res != false)) {
                    dialog.dismiss()
                }
            } catch (ex: Exception) {
            }
        }
    }

    fun showDialog(supportFragmentManager: FragmentManager, dialogTag: String) {
        closeDialog(supportFragmentManager, dialogTag)
        show(supportFragmentManager, dialogTag)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager?.beginTransaction()
            ft?.add(this, tag)
            ft?.commitAllowingStateLoss()
        } catch (e: IllegalStateException) {
        }
    }

    override fun onResume() {
        super.onResume()
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )


            // this is to show dialog at the bottom of the screen
            val window: Window? = dialog!!.window
            val wlp: WindowManager.LayoutParams = window!!.getAttributes()
            wlp.gravity = Gravity.CENTER
//        wlp.width = WindowManager.LayoutParams.MATCH_PARENT
            window.setAttributes(wlp)
        }
    }

}