package com.loud9ja.loud9ja.stream.ui.meeting.participants

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.loud9ja.loud9ja.databinding.DialogParticipantsBinding


class ParticipantsDialog(context: Context) : Dialog(context) {

    var adapter: ParticipantsAdapter
    lateinit var dialogParticipantsBinding: DialogParticipantsBinding

    init {
        adapter = ParticipantsAdapter(false, false, false, false, {}, VIEW_TYPE.PREVIEW)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogParticipantsBinding = DialogParticipantsBinding.inflate(LayoutInflater.from(context))
        setContentView(dialogParticipantsBinding.root)

        initViews()
    }

    private fun initViews() {
        dialogParticipantsBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ParticipantsDialog.adapter
        }
    }
}