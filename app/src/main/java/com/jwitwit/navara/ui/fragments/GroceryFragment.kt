package com.jwitwit.navara.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jwitwit.navara.R
import com.jwitwit.navara.adapters.GroceryAdapter
import com.jwitwit.navara.databinding.FragmentListBinding
import com.jwitwit.navara.ui.MainActivity
import com.jwitwit.navara.ui.viewmodels.GroceryViewModel
import com.jwitwit.navara.util.Resource
import kotlinx.android.synthetic.main.fragment_list.*

class GroceryFragment: Fragment(R.layout.fragment_list) {

    lateinit var viewModel: GroceryViewModel
    lateinit var groceryAdapter: GroceryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).viewModel
        return DataBindingUtil.inflate<FragmentListBinding>(
            inflater, R.layout.fragment_list, container, false
        ).apply {
            lifecycleOwner = this@GroceryFragment
            viewmodel = this@GroceryFragment.viewModel
        }?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecylerView()

        viewModel.isCbEnabled.observe(viewLifecycleOwner, Observer { shouldBeEnabled ->
            cbUnique.isEnabled = shouldBeEnabled
        })

        viewModel.groceryItem.observe(viewLifecycleOwner, Observer {value ->
            btnAdd.isEnabled = !value.isNullOrEmpty()
        })

        viewModel.singleLiveEvent.observe(viewLifecycleOwner, Observer { resource ->
            btnClear.isEnabled = viewModel.groceryList.isNotEmpty()
            when(resource) {
                is Resource.Insert -> {
                    groceryAdapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Clear -> {
                    groceryAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun setRecylerView() {
        groceryAdapter = GroceryAdapter(viewModel.groceryList)
        rvGroceryItems.apply {
            adapter = groceryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}