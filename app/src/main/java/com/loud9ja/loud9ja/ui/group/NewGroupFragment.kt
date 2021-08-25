package com.loud9ja.loud9ja.ui.group

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.loud9ja.loud9ja.databinding.FragmentNewGroupBinding
import com.loud9ja.loud9ja.utils.BindingFragment

class NewGroupFragment : BindingFragment<FragmentNewGroupBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNewGroupBinding::inflate

}