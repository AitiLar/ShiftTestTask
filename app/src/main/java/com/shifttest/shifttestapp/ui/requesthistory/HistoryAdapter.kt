package com.shifttest.shifttestapp.ui.requesthistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shifttest.shifttestapp.R
import com.shifttest.shifttestapp.model.BinListData



class HistoryAdapter(private val itemClickListener: ItemClick): ListAdapter<BinListData, HistoryAdapter.HistoryViewHolder>(HistoryViewHolder.HistoryComparator()) {
    interface ItemClick {
        fun onItemClick(position: Int)
    }
     var mClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.bin)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val historyItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            historyItemView.text = text

        }

        companion object {
            fun create(parent: ViewGroup): HistoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item_history, parent, false)

                return HistoryViewHolder(view)
            }
        }


        class HistoryComparator : DiffUtil.ItemCallback<BinListData>() {
            override fun areItemsTheSame(oldItem: BinListData, newItem: BinListData): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: BinListData, newItem: BinListData): Boolean {
                return oldItem.bin == newItem.bin
            }
        }

    }
}