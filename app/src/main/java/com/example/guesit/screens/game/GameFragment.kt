package com.example.guesit.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.guesit.R
import com.example.guesit.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,false)

        Log.i("GameFragment", "Called viewModelProvider.of!")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })
        viewModel.gameHasFinishe.observe(viewLifecycleOwner, Observer { hasFinish ->
            if (hasFinish){
                gameFinished()
                viewModel.gamefinish()
            }
        })
        return binding.root
    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value ?: 0)
        findNavController().navigate(action)
        Toast.makeText(activity,"Game finished",Toast.LENGTH_SHORT).show()
    }


}