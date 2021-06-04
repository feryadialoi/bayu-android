package dev.feryadi.bayu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.feryadi.bayu.databinding.FragmentScanBinding


class ScanFragment : Fragment() {

    private lateinit var viewBinding: FragmentScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentScanBinding.inflate(inflater, container, false)

        return viewBinding.root
    }
}