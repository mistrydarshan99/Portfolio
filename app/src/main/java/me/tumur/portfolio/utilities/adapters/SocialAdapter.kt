package me.tumur.portfolio.utilities.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.tumur.portfolio.R
import me.tumur.portfolio.database.model.SocialModel
import me.tumur.portfolio.utilities.extentions.inflate

class SocialAdapter(private val social: List<SocialModel>) : RecyclerView.Adapter<SocialAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_item_social))
    }

    override fun getItemCount() = social.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
}