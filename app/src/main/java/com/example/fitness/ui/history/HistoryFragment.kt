package com.example.fitness.ui.history

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitness.R
import com.example.fitness.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this)[HistoryViewModel::class.java]

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

        val roundedRectangles =
            arrayOf(binding.roundedRectangle, binding.roundedRectangle2, binding.roundedRectangle3)
        val activities = arrayOf(binding.joggingHistory, binding.cycling, binding.walking)
        val activityTimes =
            arrayOf(binding.activityTime, binding.activityTime2, binding.activityTime3)
        val routeLengths = arrayOf(binding.routeLenght1, binding.routeLenght2, binding.routeLenght3)
        val activityImages = arrayOf(binding.runner, binding.biker, binding.walker)

        for (i in roundedRectangles.indices) {
            ObjectAnimator.ofFloat(roundedRectangles[i], "alpha", 0f, 1f)
                .apply {
                    duration = 700
                    startDelay = (1800 + 100 * i).toLong()
                    ObjectAnimator.ofFloat(activities[i], "alpha", 0f, 1f)
                        .apply {
                            duration = 700
                            startDelay = (1800 + 100 * i).toLong()
                            ObjectAnimator.ofFloat(activityTimes[i], "translationX", -200f, 0f)
                                .apply {
                                    duration = 700
                                    startDelay = (2100 + 100 * i).toLong()
                                    ObjectAnimator.ofFloat(activityTimes[i], "alpha", 0f, 1f)
                                        .apply {
                                            duration = 700
                                            startDelay = (2100 + 100 * i).toLong()
                                        }.start()
                                }.start()
                            ObjectAnimator.ofFloat(routeLengths[i], "translationX", -200f, 0f)
                                .apply {
                                    duration = 700
                                    startDelay = (2100 + 100 * i).toLong()
                                    ObjectAnimator.ofFloat(routeLengths[i], "alpha", 0f, 1f)
                                        .apply {
                                            duration = 700
                                            startDelay = (2100 + 100 * i).toLong()
                                            ObjectAnimator.ofFloat(
                                                activityImages[i],
                                                "scaleX",
                                                0f,
                                                1f
                                            ).apply {
                                                duration = 700
                                                startDelay = (2100 + 100 * i).toLong()
                                                ObjectAnimator.ofFloat(
                                                    activityImages[i],
                                                    "scaleY",
                                                    0f,
                                                    1f
                                                ).apply {
                                                    duration = 700
                                                    startDelay = (2100 + 100 * i).toLong()
                                                    ObjectAnimator.ofFloat(
                                                        activityImages[i],
                                                        "alpha",
                                                        0f,
                                                        1f
                                                    ).apply {
                                                        duration = 700
                                                        startDelay = (2100 + 100 * i).toLong()
                                                    }.start()
                                                }.start()
                                            }.start()
                                        }.start()
                                }.start()
                        }.start()
                }.start()
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)

        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.rounded_rectangle).setOnClickListener {
            navigateToActivityDetail("Jogging")
        }
        view.findViewById<ImageView>(R.id.rounded_rectangle2).setOnClickListener {
            navigateToActivityDetail("Cycling")
        }
        view.findViewById<ImageView>(R.id.rounded_rectangle3).setOnClickListener {
            navigateToActivityDetail("Walking")
        }
    }

    private fun navigateToActivityDetail(activityName: String) {
        val bundle = Bundle().apply {
            putString("activityName", activityName)
        }
        findNavController().navigate(R.id.navigation_activitydetial, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}