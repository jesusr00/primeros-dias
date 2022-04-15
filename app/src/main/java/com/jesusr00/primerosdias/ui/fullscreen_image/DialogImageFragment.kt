package com.jesusr00.primerosdias.ui.fullscreen_image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.navOptions
import com.jesusr00.primerosdias.database.DatabaseHelper
import com.jesusr00.primerosdias.databinding.FragmentDialogImageBinding


class DialogImageFragment : DialogFragment() {

    private var photoId: Int? = null
    private var _binding: FragmentDialogImageBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(requireContext())
        arguments?.apply {
            photoId = getInt(PHOTO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo = db.getPhotoById(photoId!!)
        binding.image.setImageBitmap(photo.photo())
    }

    companion object {
        private const val PHOTO_ID = "PHOTO_ID"

        @JvmStatic
        fun newInstance(photoId: Int) =
            DialogImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(PHOTO_ID, photoId)
                }
                navOptions { launchSingleTop = true }
            }
    }
}