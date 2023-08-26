package com.example.myapplication.ui.stafflist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.repository.model.Employee


class StaffAdapter(private val staffList: List<Employee>) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_staff_list_item, parent, false)
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
        fun bind(staff: Employee) {
            itemView.findViewById<TextView>(R.id.tv_staff_name).text = staff.FullName
            itemView.findViewById<TextView>(R.id.tv_designation).text = staff.Designation
            val imageView = itemView.findViewById<ImageView>(R.id.iv_staff_profile)
            Glide.with(itemView)
                .load(staff.Image)
                .into(imageView)
        }
    }
}
