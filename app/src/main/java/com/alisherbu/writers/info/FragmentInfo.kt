package com.alisherbu.writers.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import com.alisherbu.writers.R
import com.alisherbu.writers.databinding.FragmentInfoBinding

class FragmentInfo : Fragment(R.layout.fragment_info) {
    private lateinit var binding: FragmentInfoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        binding.bookInfo.movementMethod = LinkMovementMethod.getInstance()
    }
}
