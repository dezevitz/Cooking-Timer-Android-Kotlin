package com.example.cookingtimer.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingtimer.databinding.FragmentTimerHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentTimerHomeBinding? = null

    private val binding get() = _binding!!

    private val timersList: MutableList<String> = mutableListOf()
    private val homeRecyclerAdapter: HomeRecyclerAdapter = HomeRecyclerAdapter(timersList)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        binding.timerRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.timerRecyclerView.adapter = homeRecyclerAdapter
    }

    private fun setListeners() {
        binding.addTimerButton.setOnClickListener{
            createTimerItem()
            homeRecyclerAdapter.notifyItemChanged(timersList.size-1)
        }
    }

    private fun createTimerItem(){
        val position = timersList.size
        timersList.add("Click to start")
        homeRecyclerAdapter.notifyItemChanged(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}