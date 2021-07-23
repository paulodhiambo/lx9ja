package com.loud9ja.loud9ja.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentSosBinding
import com.loud9ja.loud9ja.utils.BindingFragment


/**
 * A simple [Fragment] subclass.
 * Use the [SosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SosFragment : BindingFragment<FragmentSosBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSosBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}