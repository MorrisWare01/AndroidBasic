package com.morrisware.android.basic.developer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morrisware.android.basic.R
import com.morrisware.android.basic.databinding.DeveloperDialogFragmentBinding
import com.morrisware.android.basic.delegate.autoCleared
import com.morrisware.android.basic.ktx.restartApp

/**
 * Created by MorrisWare on 2018/8/2.
 * Email: MorrisWare01@gmail.com
 */
class DeveloperDialogFragment : DialogFragment() {

    private var binding by autoCleared<DeveloperDialogFragmentBinding>()

    lateinit var developerViewModel: DeveloperViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.developer_dialog_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        developerViewModel = ViewModelProviders.of(this)
            .get(DeveloperViewModel::class.java)
        binding.viewModel = developerViewModel
        binding.setLifecycleOwner(this)

        developerViewModel.dismissLiveData.observe(this, Observer {
            dismiss()
        })
        developerViewModel.restartAppLiveData.observe(this, Observer {
            activity?.application?.restartApp()
        })
    }

}