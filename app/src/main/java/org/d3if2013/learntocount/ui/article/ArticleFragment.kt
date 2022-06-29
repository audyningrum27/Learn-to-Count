package org.d3if2013.learntocount.ui.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2013.learntocount.databinding.FragmentArticleBinding
import org.d3if2013.learntocount.network.ApiStatus

class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this).get(ArticleViewModel::class.java)
    }

    private lateinit var binding: FragmentArticleBinding
    private lateinit var myAdapter: ArticleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        myAdapter = ArticleAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        Log.i("MainFragment", "onCreate dijalankan")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainFragment", "onStart dijalankan")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainFragment", "onResume dijalankan")
    }

    override fun onPause() {
        Log.i("MainFragment", "onPause dijalankan")
        super.onPause()
    }

    override fun onStop() {
        Log.i("MainFragment", "onStop dijalankan")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("MainFragment", "onDestroy dijalankan")
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}