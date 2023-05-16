package com.example.fitness.ui.dashboard

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitness.MainActivity
import com.example.fitness.R
import com.example.fitness.SplashScreen
import com.example.fitness.databinding.FragmentDashboardBinding
import recording

class DashboardFragment : Fragment() {

private var _binding: FragmentDashboardBinding? = null

  private val binding get() = _binding!!

    override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
): View {
  ViewModelProvider(this)[DashboardViewModel::class.java]

  _binding = FragmentDashboardBinding.inflate(inflater, container, false)
  val root: View = binding.root

  ObjectAnimator.ofFloat(binding.backImg, "translationX", 200f, 0f)
    .apply {
      duration = 700
      startDelay = 700
      ObjectAnimator.ofFloat(binding.backImg, "alpha", 0f, 1f)
        .apply {
          duration = 700
          startDelay = 700
        }.start()
    }.start()

  ObjectAnimator.ofFloat(binding.historyActivity, "translationX", 200f, 0f)
    .apply {
      duration = 700
      startDelay = 700
      ObjectAnimator.ofFloat(binding.historyActivity, "alpha", 0f, 1f)
        .apply {
          duration = 700
          startDelay = 700
        }.start()
    }.start()

  ObjectAnimator.ofFloat(binding.houseImg, "translationY", -100f, 0f)
    .apply {
      duration = 700
      startDelay = 700
      ObjectAnimator.ofFloat(binding.houseImg, "alpha", 0f, 1f)
        .apply {
          duration = 700
          startDelay = 700
        }.start()
    }.start()

  ObjectAnimator.ofFloat(binding.choseActivity, "translationY", -100f, 0f)
    .apply {
      duration = 700
      startDelay = 1400
      ObjectAnimator.ofFloat(binding.choseActivity, "alpha", 0f, 1f)
        .apply {
          duration = 700
          startDelay = 1400
        }.start()
    }.start()

  val roundedRectangles =
    arrayOf(binding.rectangle1, binding.rectangle2, binding.rectangle3)
  val activities = arrayOf(binding.jogging, binding.cyclingDashboard, binding.walk)
  val routeLengths = arrayOf(binding.tapToStart, binding.tapToStart2, binding.tapToStart3)
  val activityImages = arrayOf(binding.joggingImg, binding.cyclingImg, binding.walkImg)

  for (i in roundedRectangles.indices) {
    ObjectAnimator.ofFloat(roundedRectangles[i], "alpha", 0f, 1f)
      .apply {
        duration = 700
        startDelay = (1800 + 100 * i).toLong()
        ObjectAnimator.ofFloat(activities[i], "alpha", 0f, 1f)
          .apply {
            duration = 700
            startDelay = (1800 + 100 * i).toLong()
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
    super.onViewCreated(view, savedInstanceState)
//    view.findViewById<LinearLayout>(R.id.back).setOnClickListener {
//      findNavController().navigate(R.id.navigation_history)
//    }
    view.findViewById<LinearLayout>(R.id.back).setOnClickListener {
      val intent = Intent(activity, MainActivity::class.java)
      startActivity(intent)
    }
    view.findViewById<ImageView>(R.id.rectangle1).setOnClickListener {
      val intent = Intent(activity, recording::class.java)
      startActivity(intent)
    }

  }


  override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}