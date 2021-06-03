package dev.feryadi.bayu.adapter

import androidx.recyclerview.widget.RecyclerView
import dev.feryadi.bayu.model.network.response.UserMutationResponse

abstract class MutationAdapter(
    protected var mutations: List<UserMutationResponse>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addMutations(mutations: List<UserMutationResponse>) {
        this.mutations = mutations
    }

}