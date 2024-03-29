package com.piwew.tourismapp.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.piwew.tourismapp.MyApplication
import com.piwew.tourismapp.core.ui.TourismAdapter
import com.piwew.tourismapp.core.ui.ViewModelFactory
import com.piwew.tourismapp.databinding.FragmentFavoriteBinding
import com.piwew.tourismapp.detail.DetailTourismActivity
import com.piwew.tourismapp.detail.DetailTourismActivity.Companion.EXTRA_DATA
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val tourismAdapter = TourismAdapter()

    @Inject lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
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