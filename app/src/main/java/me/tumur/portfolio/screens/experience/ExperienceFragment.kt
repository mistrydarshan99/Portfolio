package me.tumur.portfolio.screens.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import me.tumur.portfolio.R

class ExperienceFragment : Fragment() {

    companion object {
        fun newInstance() = ExperienceFragment()
    }

    private lateinit var viewModel: ExperienceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ExperienceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
