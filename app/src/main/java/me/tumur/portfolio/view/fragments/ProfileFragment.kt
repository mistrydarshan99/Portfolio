package me.tumur.portfolio.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentProfileBinding
import me.tumur.portfolio.viewmodel.fragments.ProfileViewModel
import org.koin.androidx.viewmodel.ext.viewModel

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val vModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.apply {
            this.lifecycleOwner = this@ProfileFragment
            this.data = vModel
        }
        return binding.root
    }

}
