package com.example.fitness.ui.activityDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitness.R
import com.example.fitness.databinding.FragmentActivitydetailBinding

class ActivityDetailFragment : Fragment() {

    private var _binding: FragmentActivitydetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitydetailBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityName = arguments?.getString("activityName")
        val activityNameTextView = view.findViewById<TextView>(R.id.activityNameTextView)
        activityNameTextView.text = activityName


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
