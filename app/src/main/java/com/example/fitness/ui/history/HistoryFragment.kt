package com.example.fitness.ui.history

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.databinding.FragmentHomeBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[HistoryViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Tworzymy animację i uruchamiamy ją dla widoku tekstowego
        ObjectAnimator.ofFloat(binding.historyPage, "translationY", -100f, 0f)
            .apply {
                duration = 700
                startDelay = 700
                ObjectAnimator.ofFloat(binding.historyPage, "alpha", 0f, 1f)
                    .apply {
                        duration = 700
                        startDelay = 700
                    }.start()
            }.start()

        ObjectAnimator.ofFloat(binding.lastActv, "translationY", -100f, 0f)
            .apply {
                duration = 700
                startDelay = 1400
                ObjectAnimator.ofFloat(binding.lastActv, "alpha", 0f, 1f)
                    .apply {
                        duration = 700
                        startDelay = 1400
                    }.start()
            }.start()

        ObjectAnimator.ofFloat(binding.roundedRectangle, "alpha", 0f, 1f)
            .apply {
                duration = 700
                startDelay = 1800
            }.start()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}