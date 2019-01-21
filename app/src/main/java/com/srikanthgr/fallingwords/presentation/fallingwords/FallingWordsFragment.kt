package com.srikanthgr.fallingwords.presentation.fallingwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.srikanthgr.fallingwords.R
import com.srikanthgr.fallingwords.presentation.base.BaseFragment
import com.srikanthgr.fallingwords.presentation.model.FallingWords


/**
 * Falling fragment which consists of providing the falling word animated text and validate based on the user response.
 */
class FallingWordsFragment : BaseFragment() {
    private var binding: com.srikanthgr.fallingwords.databinding.FallingWordsFragmentBinding? = null
    private lateinit var challengeWord: TextView
    private lateinit var translatedWord: TextView
    private lateinit var fallingWordAnimation: Animation
    private lateinit var translationButton: Button
    private var isCorrect: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.falling_words_fragment, container, false)
        binding = DataBindingUtil.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        translatedWord = binding!!.translatedWord
        translationButton = binding!!.validateWord
        challengeWord = binding!!.challengeWord
        fallingWordAnimation = loadAnimation(activity, R.anim.falling_animation)
        val fallingWordsModel: FallingWordsModel? =
            ViewModelProviders.of(this, viewModelFactory).get(FallingWordsModel::class.java)
        fallingWordsModel?.fallingWords()
        //adding the corresponding listener
        fallingWordsModel?.result?.observe(this, changeObserver)
        translatedWord.startAnimation(fallingWordAnimation)
        fallingWordAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                updateDashBoard()
                fallingWordsModel?.fallingWords()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        translationButton.setOnClickListener {
            updateDashBoard()
            fallingWordsModel?.fallingWords()
        }
    }

    /**
     * Update the dashboard based on the user feed back if the challenge word is the same as translated word the corresponding counter will be updated.
     *
     */
    private fun updateDashBoard() {
        if (isCorrect) {
            //Update the correct counter
            updateDashboardCounter(binding!!.correctWord)
        } else {
            //update the incorrect counter.
            updateDashboardCounter(binding!!.inCorrectWord)
        }
    }

    /**
     * Observe the value from the viewmodel class which animates the textview and set the values of each textview.
     *
     */
    private val changeObserver =
        Observer<FallingWords> { value ->
            value?.let {
                isCorrect = value.isCorrect
                challengeWord.text = value.challengeWord
                translatedWord.text = value.translatedWord
                translatedWord.startAnimation(fallingWordAnimation)
            }
        }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    /**
     * Update the counter dashboard.
     */
    private fun updateDashboardCounter(counterView: TextView) {
        counterView.text = String.format("%d", Integer.parseInt(counterView.text as String) + 1)
    }
}