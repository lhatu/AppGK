package com.example.appgk.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.appgk.databinding.FragmentHoanHaoBinding
import com.example.appgk.viewmodel.HoanHaoViewModel


class HoanHaoFragment : Fragment() {

    private var _binding: FragmentHoanHaoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HoanHaoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoanHaoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HoanHaoViewModel::class.java)

        binding.checkButton.setOnClickListener {
            val input = binding.inputNumber.text.toString()

            if (input.isEmpty()) {
                binding.resultText.text = "Vui lòng nhập một số!"
                return@setOnClickListener
            }

            val number = input.toIntOrNull()
            if (number == null || number <= 0) {
                binding.resultText.text = "Vui lòng nhập một số nguyên dương!"
                return@setOnClickListener
            }

            if (number != null) {
                viewModel.checkPerfectNumber(number)
            } else {
                binding.resultText.text = "Vui lòng nhập số hợp lệ"
            }

            binding.inputNumber.text?.clear()
        }

        viewModel.result.observe(viewLifecycleOwner) {
            binding.resultText.text = it
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}