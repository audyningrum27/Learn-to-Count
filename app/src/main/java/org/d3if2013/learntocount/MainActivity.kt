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
        binding.reset.setOnClickListener { clearText() }
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

        var marks = 0
        var tanda = ""
        val benar = "Benar"
        val salah = "Salah"
        var nilai = 0

        val rightAnswer1 = "48"
        val rightAnswer2 = soal2 == R.id.soal2_pil3
        val ra2 = getSoal2(rightAnswer2)
        val rightAnswer3 = "39"
        val rightAnswer4 = soal4 == R.id.soal4_pil1
        val ra4 = getSoal4(rightAnswer4)
        val rightAnswer5 = "100"

        if (soal1 == rightAnswer1) {
            marks++
            binding.no1TextView.text = getString(R.string.one, soal1, benar)
        } else {
            binding.no1TextView.text = getString(R.string.one, soal1, salah)
        }

        if(rightAnswer2) {
            marks++
            binding.no2TextView.text = getString(R.string.two, ra2, benar)
        } else {
            binding.no2TextView.text = getString(R.string.two, ra2, salah)
        }

        if (soal3 == rightAnswer3) {
            marks++
            binding.no3TextView.text = getString(R.string.three, soal3, benar)
        } else {
            binding.no3TextView.text = getString(R.string.three, soal3, salah)
        }

        if(rightAnswer4) {
            marks++
            binding.no4TextView.text = getString(R.string.four, ra4, benar)
        } else{
            binding.no4TextView.text = getString(R.string.four, ra4, salah)
        }

        if (soal5 == rightAnswer5) {
            marks++
            binding.no5TextView.text = getString(R.string.five, soal5, benar)
        } else {
            binding.no5TextView.text = getString(R.string.five, soal5, salah)
        }

        if(marks == 5){
            tanda = "5 dari 5 soal"
            nilai = 100
            binding.marksTextView.text = getString(R.string.marks, tanda)
            binding.gradeTextView.text = getString(R.string.grade, nilai)
        } else if(marks == 4) {
            tanda = "4 dari 5 soal"
            nilai = 80
            binding.marksTextView.text = getString(R.string.marks, tanda)
            binding.gradeTextView.text = getString(R.string.grade, nilai)
        } else if(marks == 3) {
            tanda = "3 dari 5 soal"
            nilai = 60
            binding.marksTextView.text = getString(R.string.marks, tanda)
            binding.gradeTextView.text = getString(R.string.grade, nilai)
        } else if(marks == 2) {
            tanda = "2 dari 5 soal"
            nilai = 40
            binding.marksTextView.text = getString(R.string.marks, tanda)
            binding.gradeTextView.text = getString(R.string.grade, nilai)
        } else if(marks == 1) {
            tanda = "1 dari 5 soal"
            nilai = 20
            binding.marksTextView.text = getString(R.string.marks, tanda)
            binding.gradeTextView.text = getString(R.string.grade, nilai)
        } else {
            tanda = "0 dari 5 soal"
            nilai = 0
            binding.marksTextView.text = getString(R.string.marks, tanda)
            binding.gradeTextView.text = getString(R.string.grade, nilai)
        }
    }

    private fun getSoal2(rightAnswer2:Boolean): String {
        val soal2 = binding.soal2RadioGroup.checkedRadioButtonId
        val stringRes = if(rightAnswer2) {
            R.string.soal2_pil3
        } else if (soal2 == R.id.soal2_pil1) {
            R.string.soal2_pil1
        } else {
            R.string.soal2_pil2
        }
        return getString(stringRes)
    }

    private fun getSoal4(rightAnswer4:Boolean): String {
        val soal4 = binding.soal4RadioGroup.checkedRadioButtonId
        val stringRes = if(rightAnswer4) {
            R.string.soal4_pil1
        } else if (soal4 == R.id.soal4_pil2) {
            R.string.soal4_pil2
        } else {
            R.string.soal4_pil3
        }
        return getString(stringRes)
    }

    private fun clearText() {
        binding.soal1Inp.setText("")
        binding.soal2RadioGroup.clearCheck()
        binding.soal3Inp.setText("")
        binding.soal4RadioGroup.clearCheck()
        binding.soal5Inp.setText("")
        binding.marksTextView.setText("")
        binding.gradeTextView.setText("")
        binding.no1TextView.setText("")
        binding.no2TextView.setText("")
        binding.no3TextView.setText("")
        binding.no4TextView.setText("")
        binding.no5TextView.setText("")
    }
}