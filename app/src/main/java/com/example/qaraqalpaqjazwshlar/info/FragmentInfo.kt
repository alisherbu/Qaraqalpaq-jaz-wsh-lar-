package com.example.qaraqalpaqjazwshlar.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import kotlinx.android.synthetic.main.fragment_info.*

class FragmentInfo : Fragment(R.layout.fragment_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book_info.movementMethod = LinkMovementMethod.getInstance()
    }

}