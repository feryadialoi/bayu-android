package dev.feryadi.bayu.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import dev.feryadi.bayu.component.MutationItem
import dev.feryadi.bayu.databinding.MutationItemLayoutBinding
import dev.feryadi.bayu.model.network.response.UserMutationResponse

class MutationAdapterImpl(mutations: List<UserMutationResponse>) : MutationAdapter(mutations) {

    class ViewHolder(val viewBinding: MutationItemLayoutBinding): RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding = MutationItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder

        val mutation = mutations[position]

        MutationItem(holder.viewBinding)
            .setAmount(mutation.amount)
            .setDescription(mutation.description)
            .setCreatedDate(mutation.createdDate)
    }

    override fun getItemCount(): Int {
        return mutations.size
    }

}