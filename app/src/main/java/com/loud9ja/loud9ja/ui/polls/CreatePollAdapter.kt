package com.loud9ja.loud9ja.ui.polls

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loud9ja.loud9ja.R

class CreatePollAdapter : RecyclerView.Adapter<CreatePollAdapter.CreatePollViewHolder>() {
    private val questions: MutableList<String> = ArrayList()

    fun addItems(items: List<String>) {
        this.questions.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: String) {
        this.questions.add(item)
        notifyDataSetChanged()
    }

    inner class CreatePollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: String) {
            itemView.findViewById<TextView>(R.id.textView10).text = question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatePollViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vote_option_item, parent, false)
        return CreatePollViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreatePollViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}