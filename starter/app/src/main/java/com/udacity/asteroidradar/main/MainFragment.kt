package com.udacity.asteroidradar.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.AsteroidDatabase
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.main.adapter.AsteroidAdapter

class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    lateinit var adapter : AsteroidAdapter
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter = AsteroidAdapter( object : AsteroidAdapter.OnClick {
            override fun onAsteroidClickListener(asteroid: AsteroidDatabase) {
                binding.root.findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
            }

        })
        binding.asteroidRecycler.adapter = adapter


        setHasOptionsMenu(true)
        viewModel.asteroids.observe(viewLifecycleOwner, Observer {
            it?.let(adapter::submitList)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.picture.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                Log.i("onViewCreated", it.url)
                binding.activityMainImageOfTheDay.setImageURI(it.url)
                binding.textView.text = "Image of the Day | ${it.title}"
                binding.textView.contentDescription =  "Image of the Day | ${it.title}"
                binding.activityMainImageOfTheDay.contentDescription = ("Image of the Day | ${it.title}")
            }
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
