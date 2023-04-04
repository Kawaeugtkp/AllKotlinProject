package jp.gardenall.viewbindingrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.gardenall.viewbindingrv.databinding.RecyclerviewItemBinding

class MainAdapter(val taskList: List<Task>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val itemBinding: RecyclerviewItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        // itemBindingの決め方で呼び出せるlayoutのxmlファイルを決めている。例えば今回recycleview_item.xmlを呼び出しているが、
        // そのためにはrecycleview_item.xml → RecycleviewItem + Binding → RecyclerviewItemBindingというもののからなる
        // variableを作成して使う必要がある。
        fun bindItem(task: Task){
            itemBinding.titleTv.text = task.title
            itemBinding.timeTv.text = task.timestamp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindItem(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}