package com.morrisware.android.basic.devsupport

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morrisware.android.basic.R
import com.morrisware.android.basic.databinding.DevSupportDialogFragmentBinding
import com.morrisware.android.basic.delegate.autoCleared
import com.morrisware.android.basic.ktx.restartApp


/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
class DevSupportDialogFragment : DialogFragment() {

    private var binding by autoCleared<DevSupportDialogFragmentBinding>()

    lateinit var devSupportViewModel: DevSupportViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dev_support_dialog_fragment,
            container,
            false
        )
        val displayMetrics = DisplayMetrics()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.windowManager.defaultDisplay.getMetrics(displayMetrics)
        dialog.window.setLayout((displayMetrics.widthPixels * 0.9f).toInt(),
            (displayMetrics.heightPixels * 0.9f).toInt())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        devSupportViewModel = ViewModelProviders.of(this)
            .get(DevSupportViewModel::class.java)
        binding.viewModel = devSupportViewModel
        binding.setLifecycleOwner(this)

        devSupportViewModel.dismissLiveData.observe(this, Observer {
            dismiss()
        })
        devSupportViewModel.restartAppLiveData.observe(this, Observer {
            activity?.application?.restartApp()
        })
    }

}