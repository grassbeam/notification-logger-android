package net.leopisang.notificationlog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.leopisang.notificationlog.NotificationLogApplication
import net.leopisang.notificationlog.R
import net.leopisang.notificationlog.adapter.HomeNotificationAdapter

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(( requireActivity().application as NotificationLogApplication).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        homeViewModel =
//                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textViewPageHeader: TextView = root.findViewById(R.id.text_page_home_title)


        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_list_notification)
        val adapter = HomeNotificationAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)



        homeViewModel.textPageHeader.observe(viewLifecycleOwner, Observer {
            textViewPageHeader.text = it
        })


        homeViewModel.allNotificationDataWithIcon.observe(viewLifecycleOwner, { notif ->
            notif.let {
                adapter.submitList(notif)
            }
        })

        return root
    }
}