package com.loud9ja.loud9ja.ui.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.button.MaterialButton
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.databinding.ActivityGettingStartedBinding
import com.loud9ja.loud9ja.ui.authentication.LoginActivity
import com.loud9ja.loud9ja.ui.authentication.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class GettingStartedActivity : AppCompatActivity() {
    lateinit var binding: ActivityGettingStartedBinding

    private var onboardingAdapter: OnboardingAdapter? = null
    private var layoutOnboardingIndicator: LinearLayout? = null
    private var buttonOnboardingAction: MaterialButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGettingStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutOnboardingIndicator = binding.layoutOnboardingIndicators
        buttonOnboardingAction = binding.buttonOnBoardingAction

        setOnboardingItem()

        val onboardingViewPager = binding.onboardingViewPager
        onboardingViewPager.adapter = onboardingAdapter

        setOnboadingIndicator()
        setCurrentOnboardingIndicators(0)

        onboardingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicators(position)
            }
        })

        binding.buttonOnBoardingAction.setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingAdapter!!.itemCount) {
                onboardingViewPager.currentItem = onboardingViewPager.currentItem + 1
            } else {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                intent.apply {
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK

                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setOnboadingIndicator() {
        val indicators = onboardingAdapter?.itemCount?.let { arrayOfNulls<ImageView>(it) }
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        if (indicators != null) {
            for (i in indicators.indices) {
                indicators[i] = ImageView(applicationContext)
                indicators[i]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.onboarding_indicator_inactive
                    )
                )
                indicators[i]!!.layoutParams = layoutParams
                layoutOnboardingIndicator!!.addView(indicators[i])
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setCurrentOnboardingIndicators(index: Int) {
        val childCount: Int = layoutOnboardingIndicator!!.childCount
        for (i in 0 until childCount) {
            val imageView = layoutOnboardingIndicator!!.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_inactive
                    )
                )
            }
        }
        if (index == onboardingAdapter?.itemCount?.minus(1) ?: 0) {
            buttonOnboardingAction?.text = "Start"
        } else {
            buttonOnboardingAction?.text = "Next"
        }
    }

    private fun setOnboardingItem() {
        val onBoardingItems: MutableList<OnBoardingItem> = ArrayList()
        val itemFastFood = OnBoardingItem()
        itemFastFood.title = "Discussion Boards"
        itemFastFood.description =
            "Start a topic of interest and read from a broad audience base. Loud9jarians are waiting to to share uncommon insights and ideas already!"
        itemFastFood.image = R.drawable.onboarding_report
        val itemPayOnline = OnBoardingItem()
        itemPayOnline.title = "Go Live"
        itemPayOnline.description =
            "Go LIVE and get views from around the world. Create your story, broadcast your interests and grow your crowd."
        itemPayOnline.image = R.drawable.onboarding_live
        val itemEatTogether = OnBoardingItem()
        itemEatTogether.title = "Report"
        itemEatTogether.description =
            "Aggression from Law Enforcement? Bribery in progress or just plain  civil disobedience? Report now on Loud9ja and let your voice be heard."
        itemEatTogether.image = R.drawable.onboarding_announce
        onBoardingItems.add(itemPayOnline)
        onBoardingItems.add(itemFastFood)
        onBoardingItems.add(itemEatTogether)
        onboardingAdapter =
            OnboardingAdapter(onBoardingItems)
    }

}