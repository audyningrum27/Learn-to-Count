package org.d3if2013.learntocount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if2013.learntocount.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener { submitClick() }
    }

    private fun submitClick() {
        val soal1 = binding.soal1Inp.text.toString()
        val soal2 = binding.soal2RadioGroup.checkedRadioButtonId
        val soal3 = binding.soal3Inp.text.toString()
        val soal4 = binding.soal4RadioGroup.checkedRadioButtonId
        val soal5 = binding.soal5Inp.text.toString()

        // Valid Input Test
        if (TextUtils.isEmpty(soal1)) {
            Toast.makeText(this, R.string.jwb1_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (soal2 == -1) {
            Toast.makeText(this, R.string.jwb2_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(soal3)) {
            Toast.makeText(this, R.string.jwb3_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (soal4 == -1) {
            Toast.makeText(this, R.string.jwb4_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(soal5)) {
            Toast.makeText(this, R.string.jwb5_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if(!soal1.matches(".*[0-9].*".toRegex())
            || !soal3.matches(".*[0-9].*".toRegex())
            || !soal5.matches(".*[0-9].*".toRegex())) {
            Toast.makeText(this, R.string.hanya_angka, Toast.LENGTH_LONG).show()
            return
        }
    }
}