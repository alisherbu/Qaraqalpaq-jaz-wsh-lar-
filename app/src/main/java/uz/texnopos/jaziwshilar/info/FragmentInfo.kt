package uz.texnopos.jaziwshilar.info

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_info.*
import uz.texnopos.jaziwshilar.R

class FragmentInfo : Fragment(R.layout.fragment_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book_info.movementMethod = LinkMovementMethod.getInstance()
    }
}
