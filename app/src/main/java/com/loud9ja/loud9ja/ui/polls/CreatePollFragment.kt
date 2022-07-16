package com.loud9ja.loud9ja.ui.polls

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.FragmentCreatePollBinding
import com.loud9ja.loud9ja.domain.firebase.poll.CreatePollRequest
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.BindingFragment
import com.loud9ja.loud9ja.utils.UIstate
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.random.Random

@AndroidEntryPoint
class CreatePollFragment : BindingFragment<FragmentCreatePollBinding>() {
    private val viewModel: PollsViewModel by viewModels()
    private lateinit var database: DatabaseReference
    private val createPollAdapter: CreatePollAdapter by lazy { CreatePollAdapter() }
    private val questions: MutableList<String> = ArrayList()
    private lateinit var dialog: Dialog
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCreatePollBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createPollAdapter.addItems(questions)
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val user = AuthPreference(requireContext()).getAuthDetails()
        database = FirebaseDatabase.getInstance().reference
        binding.optionsRecycler.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = createPollAdapter
        }

        binding.createPoll.setOnClickListener {
            showDialog()
        }
        binding.btnCreate.setOnClickListener {
            if (binding.pollQuiz.text.toString().trim().isBlank() || questions.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please add poll question and options",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.progressBar2.visibility = View.VISIBLE
                //create poll
                val date = Calendar.getInstance().time
                val poll = CreatePollRequest(
                    24,
                    0,
                    0,
                    questions,
                    binding.pollQuiz.text.toString(),
                    "$date",
                    "${user?.image}",
                    "${user?.userName}"
                )
                createNewPoll(poll)
            }
        }
    }

    private fun createNewPoll(poll: CreatePollRequest) {
        val postId = Random(99999999).nextInt()
        database.child("poll").child(postId.toString())
            .setValue(poll)
        viewModel.createVote(poll)
        observe()

    }

    private fun observe() {
        viewModel.createPollResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is UIstate.Success -> {
                    Toast.makeText(requireContext(), "Poll Created successfully", Toast.LENGTH_LONG)
                        .show()
                    binding.progressBar2.visibility = View.GONE
                    val fragment = PollsFragment()
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(
                        R.id.nav_host_fragment_content_main,
                        fragment
                    )
                    fragmentTransaction?.replace(R.id.nav_host_fragment_content_main, fragment)
                    fragmentTransaction?.commit()
                }
                is UIstate.Loading -> {}
                is UIstate.Error -> {
                    Toast.makeText(requireContext(), "Unable to create poll", Toast.LENGTH_LONG)
                        .show()
                    binding.progressBar2.visibility = View.GONE
                }
            }
        }
    }

    private fun showDialog() {
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.create_poll_option)
        val question = dialog.findViewById(R.id.poll_input) as TextInputEditText
        val saveButton = dialog.findViewById(R.id.save_btn) as Button
        val cancelButton = dialog.findViewById(R.id.cancel_btn) as Button
        saveButton.setOnClickListener {
            if (question.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Please input option", Toast.LENGTH_LONG).show()
            } else {
                questions.add(question.text.toString())
                createPollAdapter.addItem(question.text.toString())
                dialog.dismiss()
            }
        }
        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

}