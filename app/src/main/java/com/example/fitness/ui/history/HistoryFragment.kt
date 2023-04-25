package com.example.fitness.ui.history

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitness.R
import com.example.fitness.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[HistoryViewModel::class.java]

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Tworzymy animację i uruchamiamy ją dla widoku tekstowego
        ObjectAnimator.ofFloat(binding.notificationBell, "translationX", 200f, 0f)
            .apply {
                duration = 700
                startDelay = 700
                ObjectAnimator.ofFloat(binding.notificationBell, "alpha", 0f, 1f)
                    .apply {
                        duration = 700
                        startDelay = 700
                    }.start()
            }.start()

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
                ObjectAnimator.ofFloat(binding.running, "alpha", 0f, 1f)
                    .apply {
                        duration = 700
                        startDelay = 1800
                    }.start()
            }.start()

        ObjectAnimator.ofFloat(binding.roundedRectangle2, "alpha", 0f, 1f)
            .apply {
                duration = 700
                startDelay = 1900
                ObjectAnimator.ofFloat(binding.cycling, "alpha", 0f, 1f)
                    .apply {
                        duration = 700
                        startDelay = 1900
                    }.start()
            }.start()

        ObjectAnimator.ofFloat(binding.roundedRectangle3, "alpha", 0f, 1f)
            .apply {
                duration = 700
                startDelay = 2000
                ObjectAnimator.ofFloat(binding.swimming, "alpha", 0f, 1f)
                    .apply {
                        duration = 700
                        startDelay = 2000
                    }.start()
            }.start()

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.notification_bell).setOnClickListener {

        findNavController().navigate(R.id.navigation_notifications)
    }
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}