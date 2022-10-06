package com.aziza.photosapp.ui.home

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aziza.photosapp.R
import com.aziza.photosapp.databinding.FragmentHomeBinding
import com.stfalcon.imageviewer.StfalconImageViewer
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class HomeFragment : Fragment(), IHHomeOnClickListener {
    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllPhoto()
        setUpRecyclerView()
    }

    private fun getAllPhoto() {
        homeViewModel.getAllPhoto()
        homeViewModel.photoResult.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()){
                hideShimmerEffect()
                homeAdapter.setData(it)
                Log.e("TAG", "getAllPhoto:${it[0].url} " )
            }

        }

    }

    private fun setUpRecyclerView() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(activity)
            val alphaAdapter = AlphaInAnimationAdapter(homeAdapter)
            val scaleAdapter = ScaleInAnimationAdapter(alphaAdapter).apply {
                setDuration(500)
                setFirstOnly(false)
            }
            adapter = scaleAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //binding=null
    }

    private fun hideShimmerEffect() {
        binding.shimmerLayout.apply {
            visibility = View.GONE
            stopShimmer()
        }
        binding.rvHome.visibility = View.VISIBLE

    }

    override fun onItemClicked() {
        Toast.makeText(requireContext(), "Click at Photo Instead !", Toast.LENGTH_SHORT).show()
    }

    override fun onPhotoClicked(photo: Photo) {
//        StfalconImageViewer.Builder<Image>() { view, image ->
//            Picasso.get().load(image.url).into(view)
//        }.show()

//        StfalconImageViewer.Builder<String>(requireContext(), photo.url, ::loadImage)
//           // .withStartPosition(startPosition)
//            .withBackgroundColor(R.color.purple_500)
//            //.withBackgroundColorResource(R.color.color)
//            .withOverlayView(view)
//            .withImagesMargin(R.dimen.margin)
//            //.withImageMarginPixels(margin)
//            .withContainerPadding(R.dimen.padding)
//            //.withContainerPadding(R.dimen.paddingStart, R.dimen.paddingTop, R.dimen.paddingEnd, R.dimen.paddingBottom)
//            //.withContainerPaddingPixels(padding)
//            //.withContainerPaddingPixels(paddingStart, paddingTop, paddingEnd, paddingBottom)
////            .withHiddenStatusBar(shouldHideStatusBar)
////            .allowZooming(isZoomingAllowed)
////            .allowSwipeToDismiss(isSwipeToDismissAllowed)
////            .withTransitionFrom(targeImageView)
//            .withImageChangeListener(::onImageChanged)
//            .withDismissListener(::onViewerDismissed)
//            .withDismissListener(::onViewerDismissed)
    }
}

