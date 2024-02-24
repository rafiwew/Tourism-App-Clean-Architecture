package com.piwew.tourismapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.piwew.tourismapp.R
import com.piwew.tourismapp.core.data.source.Resource
import com.piwew.tourismapp.core.ui.TourismAdapter
import com.piwew.tourismapp.core.ui.ViewModelFactory
import com.piwew.tourismapp.databinding.FragmentHomeBinding
import com.piwew.tourismapp.detail.DetailTourismActivity
import com.piwew.tourismapp.detail.DetailTourismActivity.Companion.EXTRA_DATA

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val tourismAdapter = TourismAdapter()
    private val viewModel by viewModels<HomeViewModel> { ViewModelFactory.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeTourismData()
    }

    private fun setupRecyclerView() {
        tourismAdapter.onItemClick = { selectedData ->
            startActivity(Intent(activity, DetailTourismActivity::class.java)
                .apply { putExtra(EXTRA_DATA, selectedData) }
            )
        }

        with(binding.rvTourism) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tourismAdapter
        }
    }

    private fun observeTourismData() {
        viewModel.tourism.observe(requireActivity()) { result ->
            showLoading(result is Resource.Loading)
            when (result) {
                is Resource.Success -> tourismAdapter.submitList(result.data)
                is Resource.Error -> showErrorMessage(result.message)
                else -> {}
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorMessage(errorMessage: String?) {
        with(binding.viewError) {
            root.visibility = View.VISIBLE
            tvError.text = errorMessage ?: getString(R.string.something_wrong)
        }
    }
}