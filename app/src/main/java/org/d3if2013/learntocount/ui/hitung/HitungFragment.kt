package org.d3if2013.learntocount.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2013.learntocount.R
import org.d3if2013.learntocount.databinding.FragmentHitungBinding
import org.d3if2013.learntocount.db.LtcDb

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = LtcDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.answerKey.setVisibility(View.GONE)
        binding.shareBtn.setVisibility(View.GONE)

        binding.submit.setOnClickListener {
            submitClick()
            binding.answerKey.setVisibility(View.VISIBLE)
            binding.shareBtn.setVisibility(View.VISIBLE)
        }

        binding.reset.setOnClickListener {
            clearText()
            binding.answerKey.setVisibility(View.GONE)
            binding.shareBtn.setVisibility(View.GONE)
        }

        binding.answerKey.setOnClickListener{
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_answerKeyFragment
            )
        }

        binding.shareBtn.setOnClickListener{
            shareData()
        }
    }

    private fun submitClick() {
        val soal1 = binding.soal1Inp.text.toString()
        val soal2 = binding.soal2RadioGroup.checkedRadioButtonId
        val soal3 = binding.soal3Inp.text.toString()
        val soal4 = binding.soal4RadioGroup.checkedRadioButtonId
        val soal5 = binding.soal5Inp.text.toString()

        // Valid Input Test
        if (TextUtils.isEmpty(soal1)) {
            Toast.makeText(context, R.string.jwb1_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (soal2 == -1) {
            Toast.makeText(context, R.string.jwb2_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(soal3)) {
            Toast.makeText(context, R.string.jwb3_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (soal4 == -1) {
            Toast.makeText(context, R.string.jwb4_invalid, Toast.LENGTH_LONG).show()
            return
        }

        if (TextUtils.isEmpty(soal5)) {
            Toast.makeText(context, R.string.jwb5_invalid, Toast.LENGTH_LONG).show()
            return
        }

        //Jawaban benar atau salah
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

        when (marks) {
            5 -> {
                tanda = "5 dari 5 soal"
                nilai = 100
                binding.marksTextView.text = getString(R.string.marks, tanda)
                binding.gradeTextView.text = getString(R.string.grade, nilai)
            }
            4 -> {
                tanda = "4 dari 5 soal"
                nilai = 80
                binding.marksTextView.text = getString(R.string.marks, tanda)
                binding.gradeTextView.text = getString(R.string.grade, nilai)
            }
            3 -> {
                tanda = "3 dari 5 soal"
                nilai = 60
                binding.marksTextView.text = getString(R.string.marks, tanda)
                binding.gradeTextView.text = getString(R.string.grade, nilai)
            }
            2 -> {
                tanda = "2 dari 5 soal"
                nilai = 40
                binding.marksTextView.text = getString(R.string.marks, tanda)
                binding.gradeTextView.text = getString(R.string.grade, nilai)
            }
            1 -> {
                tanda = "1 dari 5 soal"
                nilai = 20
                binding.marksTextView.text = getString(R.string.marks, tanda)
                binding.gradeTextView.text = getString(R.string.grade, nilai)
            }
            else -> {
                tanda = "0 dari 5 soal"
                nilai = 0
                binding.marksTextView.text = getString(R.string.marks, tanda)
                binding.gradeTextView.text = getString(R.string.grade, nilai)
            }
        }
    }

    private fun getSoal2(rightAnswer2:Boolean): String {
        val soal2 = binding.soal2RadioGroup.checkedRadioButtonId
        val stringRes = when {
            rightAnswer2 -> {
                R.string.soal2_pil3
            }
            soal2 == R.id.soal2_pil1 -> {
                R.string.soal2_pil1
            }
            else -> {
                R.string.soal2_pil2
            }
        }
        return getString(stringRes)
    }

    private fun getSoal4(rightAnswer4:Boolean): String {
        val soal4 = binding.soal4RadioGroup.checkedRadioButtonId
        val stringRes = when {
            rightAnswer4 -> {
                R.string.soal4_pil1
            }
            soal4 == R.id.soal4_pil2 -> {
                R.string.soal4_pil2
            }
            else -> {
                R.string.soal4_pil3
            }
        }
        return getString(stringRes)
    }

    private fun clearText() {
        binding.soal1Inp.setText("")
        binding.soal2RadioGroup.clearCheck()
        binding.soal3Inp.setText("")
        binding.soal4RadioGroup.clearCheck()
        binding.soal5Inp.setText("")
        binding.marksTextView.text = ""
        binding.gradeTextView.text = ""
        binding.no1TextView.text = ""
        binding.no2TextView.text = ""
        binding.no3TextView.text = ""
        binding.no4TextView.text = ""
        binding.no5TextView.text = ""
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }

            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        val message = getString(R.string.share_template,
            binding.marksTextView.text,
            binding.gradeTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}