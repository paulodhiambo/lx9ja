package com.loud9ja.loud9ja.ui.discusion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentDiscussionBinding
import com.loud9ja.loud9ja.domain.Trending
import com.loud9ja.loud9ja.ui.home.RecentRecyclerViewAdapter
import com.loud9ja.loud9ja.ui.report.ReportDetailFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DiscussionFragment : Fragment() {

    private var _binding: FragmentDiscussionBinding? = null
    private val trendingAdapter by lazy {
        TrendingRecyclerViewAdapter()
    }
    private val recentAdapter by lazy {
        RecentRecyclerViewAdapter()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDiscussionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.trendingRecyclerview.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
            trendingAdapter.addItems(
                mutableListOf(
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", "")
                )
            )
            trendingAdapter.listener = { _, _, _ ->
                val fragment = DiscussionDetailFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.commit()
            }
        }
        binding.recentRecyclerview.apply {
            hasFixedSize()
            isNestedScrollingEnabled = false
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = recentAdapter
            recentAdapter.addItems(
                mutableListOf(
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", ""),
                    Trending("", "")
                )
            )
            recentAdapter.listener = { _, _, _ ->
                val fragment = DiscussionDetailFragment()
                val fragmentManager = fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                fragmentTransaction?.commit()
            }
        }
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}