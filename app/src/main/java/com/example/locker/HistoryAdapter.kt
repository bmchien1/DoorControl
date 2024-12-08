package com.example.locker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.locker.R
import com.example.locker.models.HistoryResponse

class HistoryAdapter(private val historyList: List<HistoryResponse.HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.fullName.text = item.fullName
        holder.idCard.text = item.idCard
        holder.openAt.text = item.openAt
    }

    override fun getItemCount() = historyList.size

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.fullName)
        val idCard: TextView = itemView.findViewById(R.id.idCard)
        val openAt: TextView = itemView.findViewById(R.id.openAt)
    }
}