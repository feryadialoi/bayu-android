package dev.feryadi.bayu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.feryadi.bayu.databinding.FragmentTransferBinding

@AndroidEntryPoint
class TransferFragment : Fragment() {

    private lateinit var viewBinding: FragmentTransferBinding

    companion object {
        @JvmStatic
        private val TAG = "TransferFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTransferBinding.inflate(inflater, container, false)

        viewBinding.tabLayoutTransfer.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        return viewBinding.root
    }
}