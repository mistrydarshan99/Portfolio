package me.tumur.portfolio.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.screen_success_profile.*
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.FragmentProfileBinding
import me.tumur.portfolio.utilities.adapters.SocialAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    // Inject viewModel
    private val vmodel: ProfileViewModel by viewModel()

    private lateinit var socialAdapter: SocialAdapter

    // Databinding
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Get databinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        // Bind data to UI
        binding.apply {
            this.lifecycleOwner = this@ProfileFragment
            this.model = vmodel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_profile_social.layoutManager = LinearLayoutManager(this.context)
        rv_profile_social.adapter = socialAdapter
    }

}
