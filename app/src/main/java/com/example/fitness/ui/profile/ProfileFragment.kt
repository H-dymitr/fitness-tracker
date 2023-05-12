package com.example.fitness.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitness.R
import com.example.fitness.utils.BMICalcUtil


class ProfileFragment : Fragment() {
    private lateinit var tvName: TextView
    private lateinit var tvLastname: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvWeight: TextView
    private lateinit var tvHeight: TextView
    private lateinit var tvBMI: TextView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val preferences = this.requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val BMIUtil = BMICalcUtil.instance

        tvName = view.findViewById(R.id.tvName)
        tvLastname = view.findViewById(R.id.tvLastName)
        tvAge = view.findViewById(R.id.tvAge)
        tvWeight = view.findViewById(R.id.tvWeight)
        tvHeight = view.findViewById(R.id.tvHeight)
        tvBMI = view.findViewById(R.id.tvBMI)

        val name = preferences.getString("name", "")
        val lastname = preferences.getString("lastname", "")
        val age = preferences.getInt("age", 0)
        val weight = preferences.getInt("weight", 0)
        val height = preferences.getInt("height", 0)
        val bmi = BMIUtil.calculateBMIMetric(height.toDouble(), weight.toDouble())

        tvName.text = name
        tvLastname.text = lastname
        tvAge.text = age.toString()
        tvWeight.text = weight.toString()
        tvHeight.text = height.toString()

        // calculateBMI
        // TODO: lint refactor
        tvBMI.text = "${BMIUtil.classifyBMI(bmi)} ( $bmi )"

        // TODO: edit profile
    }
}