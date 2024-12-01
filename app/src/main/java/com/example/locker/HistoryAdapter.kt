package com.example.locker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.locker.models.HistoryItem

class HistoryAdapter(private val historyList: List<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    // ViewHolder cho từng item trong danh sách
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTimestamp: TextView = itemView.findViewById(R.id.tv_timestamp)
        val tvUserId: TextView = itemView.findViewById(R.id.tv_user_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyList[position]
        holder.tvTimestamp.text = historyItem.timestamp
        holder.tvUserId.text = "Người dùng: ${historyItem.userId}"
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}
