package dev.feryadi.bayu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.databinding.FragmentPinBinding

@AndroidEntryPoint
class PinFragment : Fragment() {

    private lateinit var viewBinding: FragmentPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPinBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    companion object {
        @JvmStatic
        private val TAG = "PinFragment"
    }
}