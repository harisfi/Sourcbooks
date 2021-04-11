package com.hryzx.sourcbooks.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.hryzx.sourcbooks.databinding.ActivitySelectInterestBinding
import com.hryzx.sourcbooks.ui.MainActivity.Companion.EXTRA_INTEREST

class SelectInterestActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectInterestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectInterestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnContinue.setOnClickListener {
            val checkedIds: List<Int> = binding.chipGroup.checkedChipIds

            if (checkedIds.size != 4) {
                Toast.makeText(this, "Please select four subjects", Toast.LENGTH_SHORT).show()
            } else {
                val checkedTitle: ArrayList<String> = ArrayList()
                for(id in checkedIds) {
                    val chip: Chip = binding.chipGroup.findViewById(id)
                    checkedTitle.add(chip.text as String)
                }
                val intent = Intent(this@SelectInterestActivity, MainActivity::class.java)
                intent.putExtra(EXTRA_INTEREST, checkedTitle)
                startActivity(intent)
            }
        }
    }
}