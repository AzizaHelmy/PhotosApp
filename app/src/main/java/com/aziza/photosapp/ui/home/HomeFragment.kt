package com.aziza.photosapp.ui.home

import android.R
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aziza.photosapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class HomeFragment : Fragment(), IHomeOnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private var currentPage = 1
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter by lazy {
        HomeAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllPhoto()
        setUpRecyclerView()
    }

    private fun getAllPhoto() {
        homeViewModel.getAllPhoto()
        homeViewModel.photoResult.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                hideShimmerEffect()
                homeAdapter.submitList(it)
            }
        }
    }

    private fun setUpRecyclerView() {
        _binding!!.rvHome.apply {
            layoutManager = LinearLayoutManager(activity)
            val alphaAdapter = AlphaInAnimationAdapter(homeAdapter)
            val scaleAdapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                setDuration(500)
                setFirstOnly(false)
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        val totalPages= 10
                        if (currentPage !== totalPages) {
                            Toast.makeText(context, currentPage.toString() + "", Toast.LENGTH_SHORT)
                                .show()
                          //  getAllResult(++currentPage)
                            getAllPhoto()
                        }
                    }
                }
            })
            adapter = scaleAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun hideShimmerEffect() {
        _binding!!.shimmerLayout.apply {
            visibility = View.GONE
            stopShimmer()
        }
        _binding!!.rvHome.visibility = View.VISIBLE

    }

    override fun onItemClicked() {
        Toast.makeText(requireContext(), "Click at Photo Instead !", Toast.LENGTH_SHORT).show()
    }

    override fun onPhotoClicked(photo: Photo) {
        showZoomableImage(requireContext(), photo.url)
    }

    private fun showZoomableImage(context: Context, imgUrl: String) {
        val dialog = Dialog(context, R.style.Theme_DeviceDefault_Light_NoActionBar)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.setCancelable(true)
        val webView = WebView(context)
        webView.layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        webView.loadUrl(imgUrl)
        webView.settings.builtInZoomControls = true
        webView.settings.setSupportZoom(true)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.zoomOut()
        dialog.setContentView(webView)
        dialog.show()
    }
}

