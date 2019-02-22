package me.tumur.portfolio.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import me.tumur.portfolio.R
import me.tumur.portfolio.viewmodel.fragments.PortfolioViewModel

class PortfolioFragment : Fragment() {

    companion object {
        fun newInstance() = PortfolioFragment()
    }

    private lateinit var viewModel: PortfolioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PortfolioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
