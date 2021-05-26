package com.example.technpractiseandroid.tasks

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.technpractiseandroid.databinding.ItemTaskBinding
import com.example.technpractiseandroid.user.Task

class TasksAdapter (
    private var list : List<Task?>
//    private val itemClick: (Task) -> Unit
): RecyclerView.Adapter<TasksAdapter.VH> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(layoutInflater, parent, false)
        return VH(binding)
    }
//            CatHolder = CatHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.bind(list[position]!!)

    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: List<Task>){
        list = newList
        notifyDataSetChanged()
    }


    inner class VH(
        private val binding:ItemTaskBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(task: Task){
            with(binding){
                tvName.text = task.name
                tvDescription.text = task.description
                tvCategory.text = task.tag
                tvCategoryImportancy.text = task.importance
                tvTime.text = "${task.time}, ${task.date}"
            }
            when (task.importance) {
                "fast" -> {
                    binding.tvCategoryImportancy.setTextColor(Color.parseColor("#EA2413"))
                }
                "medium" -> {
                    binding.tvCategoryImportancy.setTextColor(Color.parseColor("#E3DF42"))
                }
                "can wait" -> {
                    binding.tvCategoryImportancy.setTextColor(Color.parseColor("#0CE628"))
                }
            }
        }

    }
}