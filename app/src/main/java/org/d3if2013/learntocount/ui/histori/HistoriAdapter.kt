package org.d3if2013.learntocount.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if2013.learntocount.R
import org.d3if2013.learntocount.databinding.ItemHistoriBinding
import org.d3if2013.learntocount.db.LtcEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<LtcEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<LtcEntity>() {
                override fun areItemsTheSame(
                    oldData: LtcEntity, newData: LtcEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: LtcEntity, newData: LtcEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )
        fun bind(item: LtcEntity) = with(binding) {
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            markTextView.text = root.context.getString(R.string.mark_x,
                item.mark)
            gradeTextView.text = root.context.getString(R.string.grade_x,
                item.grade)
        }
    }
}