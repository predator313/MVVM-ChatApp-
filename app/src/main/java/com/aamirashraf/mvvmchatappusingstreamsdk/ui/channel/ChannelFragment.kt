package com.aamirashraf.mvvmchatappusingstreamsdk.ui.channel


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.databinding.FragmentChannelBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

@AndroidEntryPoint
class ChannelFragment:BindingFragment<FragmentChannelBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentChannelBinding::inflate

    private val viewModel:ChannelViewModel by activityViewModels()  //means that this view model is tied the lifecycle of activity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user=viewModel.getUser()
        if(user==null){
            findNavController().popBackStack()
            return
        }
        val factory=ChannelListViewModelFactory(  //this is the factory of stream
            filter = Filters.and(
                Filters.eq("type","messaging"),
            ),
            sort = ChannelListViewModel.DEFAULT_SORT,
            limit = 30  //we load 30 channel at onces
        )
        val channelListViewModel:ChannelListViewModel by viewModels { factory }   //this is the way to pass the custom factory stream
        val channelListHeaderViewModel:ChannelListHeaderViewModel by viewModels()
        channelListViewModel.bindView(binding.channelListView,viewLifecycleOwner)
        channelListHeaderViewModel.bindView(binding.channelListHeaderView,viewLifecycleOwner)

        binding.channelListHeaderView.setOnUserAvatarClickListener{
            viewModel.getUser()
            findNavController().popBackStack()
        }


    }
}