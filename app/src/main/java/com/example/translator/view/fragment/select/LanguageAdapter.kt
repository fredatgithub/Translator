package com.example.translator.view.fragment.select

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.R
import com.example.translator.model.data.Language
import kotlinx.android.synthetic.main.item_language.view.*

class LanguageAdapter(
    private val languages: List<Language>,
    private val clickListener: (Language) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LanguageViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_language, parent, false)
    )

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]

        with(holder) {
            text.text = language.lang
            itemView.setOnClickListener { clickListener(language) }
        }
    }

    override fun getItemCount() = languages.size

    class LanguageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = view.text
    }
}