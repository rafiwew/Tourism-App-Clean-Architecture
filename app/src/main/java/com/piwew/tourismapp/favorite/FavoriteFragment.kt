package com.piwew.tourismapp.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.piwew.tourismapp.core.ui.TourismAdapter
import com.piwew.tourismapp.databinding.FragmentFavoriteBinding
import com.piwew.tourismapp.detail.DetailTourismActivity
import com.piwew.tourismapp.detail.DetailTourismActivity.Companion.EXTRA_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val tourismAdapter = TourismAdapter()
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeFavoriteTourismData()
    }

    private fun setupRecyclerView() {
        tourismAdapter.onItemClick = { selectedData ->
            startActivity(
                Intent(activity, DetailTourismActivity::class.java)
                .apply { putExtra(EXTRA_DATA, selectedData) }
            )
        }

        with(binding.rvTourism) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = tourismAdapter
        }
    }

    private fun observeFavoriteTourismData() {
        favoriteViewModel.favoriteTourism.observe(requireActivity()) { dataTourism ->
            tourismAdapter.submitList(dataTourism)
            binding.viewEmpty.root.visibility = if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }
}