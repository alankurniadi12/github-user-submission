package com.dicodingalan.sub2gituser.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicodingalan.sub2gituser.databinding.FragmentFollowBinding

class FollowingFragment: Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FollowersAdapter
    private lateinit var followersViewModel: FollowingViewModel
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(DetailActivity.NAME_KEY).toString()

        adapter = FollowersAdapter()

        binding.apply {
            rvFollowers.setHasFixedSize(true)
            rvFollowers.layoutManager = LinearLayoutManager(activity)
            rvFollowers.adapter = adapter
        }

        followersViewModel = ViewModelProvider(this)[FollowingViewModel::class.java]
        followersViewModel.apply {
            getFollowing(username)
            showLoading(true)
            responseFollowing.observe(requireActivity(), {
                showLoading(false)
                if (it != null) {
                    adapter.setListUser(it)
                }
            })
        }

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressCircular.visibility = View.VISIBLE
        }else {
            binding.progressCircular.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}