package com.loud9ja.loud9ja.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.loud9ja.loud9ja.databinding.ActivityCalculatorBinding
import com.loud9ja.loud9ja.ui.SplashActivity
import com.loud9ja.loud9ja.utils.AuthPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    private val pinData: MutableLiveData<String> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val authPreference = AuthPreference(this)
        val pin = authPreference.getPin()

        if (pin == null || pin == "") {
            binding.pin.visibility = View.VISIBLE
        }

        binding.btnOne.setOnClickListener {
            pinData.value.plus("1")
            binding.text.text = binding.text.text.toString().plus("1")
        }
        binding.btnTwo.setOnClickListener {
            pinData.value.plus("2")
            binding.text.text = binding.text.text.toString().plus("2")


        }
        binding.btnThree.setOnClickListener {
            pinData.value.plus("3")
            binding.text.text = binding.text.text.toString().plus("3")

        }
        binding.btnFour.setOnClickListener {
            // pin + "4"
            binding.text.text = binding.text.text.toString().plus("4")

        }
        binding.btnFive.setOnClickListener {
            //pin + "5"
            binding.text.text = binding.text.text.toString().plus("5")

        }
        binding.btnSix.setOnClickListener {
            //pin + "6"
            binding.text.text = binding.text.text.toString().plus("6")

        }
        binding.btnSeven.setOnClickListener {
            //pin + "7"
            binding.text.text = binding.text.text.toString().plus("7")


        }
        binding.btnEight.setOnClickListener {
            //pin + "8"
            binding.text.text = binding.text.text.toString().plus("8")

        }
        binding.btnNine.setOnClickListener {
            //pin + "9"
            binding.text.text = binding.text.text.toString().plus("9")


        }
        binding.btnZero.setOnClickListener {
            //pin + "0"
            binding.text.text = binding.text.text.toString().plus("0")


        }
        binding.btnEqual.setOnClickListener {
            if (pin == null || binding.text.toString().length > 2) {
                authPreference.savePin(binding.text.text.toString())
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            } else if (pin == binding.text.text.toString()) {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }

        binding.btnBack.setOnClickListener {
            if (binding.text.text.toString().isNotEmpty()) {
                binding.text.text = binding.text.text.toString().dropLast(1)
            }
        }

        binding.btnDelete.setOnClickListener { }
        binding.btnSmallBracket.setOnClickListener { }
        binding.btnPercentage.setOnClickListener { }
        binding.btnMultiple.setOnClickListener { }
        binding.btnMinus.setOnClickListener { }
        binding.btnPlus.setOnClickListener { }
        binding.btnDot.setOnClickListener { }
        binding.btnDivide.setOnClickListener { }


    }
}