package com.jesusr00.primerosdias.ui.feu_secretariat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jesusr00.primerosdias.adapters.FeuSecretariatAdapter
import com.jesusr00.primerosdias.database.DatabaseHelper
import com.jesusr00.primerosdias.databinding.FragmentFeuSecretariatBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FeuSecretariatFragment : Fragment() {

    private var _binding: FragmentFeuSecretariatBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DatabaseHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeuSecretariatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainScope().launch(Dispatchers.Main) {
            val feuSecretariatMembers = (async { db.getFeuSecretariatMembers() }).await()
            binding.feuSecretariatRecyclerview.adapter = FeuSecretariatAdapter(feuSecretariatMembers, childFragmentManager)
            binding.feuSecretariatRecyclerview.visibility = View.VISIBLE
            binding.feuSecretariatProgress.visibility = View.GONE
        }
    }

}