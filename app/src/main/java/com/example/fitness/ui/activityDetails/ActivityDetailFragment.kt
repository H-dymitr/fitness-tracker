package com.example.fitness.ui.activityDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.databinding.FragmentActivitydetailBinding

class ActivityDetailFragment : Fragment() {

private var _binding: FragmentActivitydetailBinding? = null

  private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activityDetailViewModel =
            ViewModelProvider(this)[ActivityDetailViewModel::class.java]

        _binding = FragmentActivitydetailBinding.inflate(inflater, container, false)

        return binding.root
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}