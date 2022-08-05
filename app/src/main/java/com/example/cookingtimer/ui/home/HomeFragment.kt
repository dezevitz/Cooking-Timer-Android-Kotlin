package com.example.cookingtimer.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingtimer.databinding.FragmentTimerHomeBinding
import java.util.*
import kotlin.concurrent.timer

class HomeFragment : Fragment() {

    private var _binding: FragmentTimerHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val timersList: MutableList<String> = mutableListOf()
    private val homeRecyclerAdapter: HomeRecyclerAdapter = HomeRecyclerAdapter(timersList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentTimerHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        binding.timerRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.timerRecyclerView.adapter = homeRecyclerAdapter
    }

    private fun setListeners() {
        binding.addTimerButton.setOnClickListener{
            val timer = createTimer()
            homeRecyclerAdapter.notifyItemChanged(timersList.size-1)
        }

        // on click of start button, timer.launch (maybe timer.start idr)
    }

    private fun createTimer(): CountDownTimer{
        val position = timersList.size
        timersList.add("Click to start")
        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timersList[position] = ((millisUntilFinished/1000).toString())
                homeRecyclerAdapter.notifyItemChanged(position)
            }

            override fun onFinish() {
                timersList[position] = "DONE"
                homeRecyclerAdapter.notifyItemChanged(position)
            }

        }
        return timer
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}