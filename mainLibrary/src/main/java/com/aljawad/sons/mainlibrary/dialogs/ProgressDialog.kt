package com.aljawad.sons.mainlibrary.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.FragmentManager
import com.aljawad.sons.mainlibrary.base.BaseDialogFragment
import com.aljawad.sons.mainlibrary.databinding.ProgressDialogLayoutBinding

class ProgressDialog : BaseDialogFragment() {

    companion object {
        const val ProgressDialog_Tag = "ProgressDialog"
        const val ProgressDialog_Message_Tag = "ProgressDialog_Message_Tag"
        fun newInstance(message: String? = null): ProgressDialog {
            val f = ProgressDialog()
            // Supply num input as an argument.
            f.isCancelable = false
            val args = Bundle()
            args.putString(ProgressDialog_Message_Tag, message)
            f.arguments = args
            return f
        }

        fun runningDialog(supportFragmentManager: FragmentManager): Dialog? {
            try {
                val fragment =
                    supportFragmentManager.findFragmentByTag(ProgressDialog_Tag)
                if (fragment != null) {
                    val channelDialog = fragment as ProgressDialog
                    return channelDialog.dialog
                }
            } catch (ex: Exception) {
            }
            return null
        }

        fun closeDialog(supportFragmentManager: FragmentManager) {
            try {
                val dialog = runningDialog(supportFragmentManager)
                dialog?.dismiss()
            } catch (ex: Exception) {
            }
        }
    }

    override var baseDialogTag: String
        get() = ProgressDialog_Tag
        set(value) {}

    fun showDialog(supportFragmentManager: FragmentManager) {
        closeDialog(supportFragmentManager)
        this.show(supportFragmentManager, ProgressDialog_Tag)
    }

    lateinit var binding: ProgressDialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProgressDialogLayoutBinding.inflate(inflater, container, false)
//        val mView = inflater.inflate(R.layout.progress_dialog_layout, container)
//        ButterKnife.bind(this, mView)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        return mView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messages = arguments?.getString(ProgressDialog_Message_Tag)
        if (!messages.isNullOrBlank()) {
            binding.message.text = messages
        }
    }

}