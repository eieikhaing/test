package com.example.myapplication.ui.stafflist

import DetailItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class StaffAdapter(private val staffList: List<DetailItem>) :
    RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_staff_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val staff = staffList[position]
        holder.bind(staff)
    }

    override fun getItemCount(): Int {
        return staffList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(staff: DetailItem) {
            itemView.findViewById<TextView>(R.id.tv_staff_name).text = staff.Name
            itemView.findViewById<TextView>(R.id.tv_designation).text = staff.Value

        }
    }
}
