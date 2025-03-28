package com.example.appgk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appgk.databinding.ActivityMainBinding
import com.example.appgk.fragment.HoanHaoFragment
import com.example.appgk.fragment.MayTinhFragment
import com.example.appgk.fragment.PhuongTrinhFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(HoanHaoFragment())
        }

        // Xử lý sự kiện chọn menu
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.HoanHao -> {
                    loadFragment(HoanHaoFragment())
                    true
                }
                R.id.PhuongTrinh -> {
                    loadFragment(PhuongTrinhFragment())
                    true
                }
                R.id.MayTinh -> {
                    loadFragment(MayTinhFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
