package app.woovictory.forearthforus.view.intro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.data.SharedPreferenceManager
import app.woovictory.forearthforus.view.account.LoginActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private val titleList = arrayListOf(
        "지구를 위한 첫 걸음\n어떤 방법들이 있을까요?"
        , "실천하고 싶은\n미션을 선택해 주세요!",
        "오늘 하루 얼만큼\n실천하였나요?"
    )

    private val subTitleList = arrayListOf(
        "흑흑! 지구가 울고 있어요ㅠㅠ"
        , "어서 지구를 도와줘야 겠어요.", "달성률을 높여 행복한 지구를 만들어주세요!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        initView()
        setUpIndicator()

        onBoardingViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                onBoardingTitle.text = titleList[position]
                onBoardingSubTitle.text = subTitleList[position]
                when (position) {
                    0, 1 -> onBoardingSkipButton.text = "건너뛰기"
                    2 -> onBoardingSkipButton.text = "시작하기"
                }
            }

        })

        // 온보딩 페이지로 왔다는 것 자체가 토큰이 존재하지 않음을 의미.
        // 따라서 로그인 페이지로 이동.
        onBoardingSkipButton.setOnClickListener {
            Intent(this, LoginActivity::class.java)
                .apply {
                    SharedPreferenceManager.userFirstState = true
                    startActivity(this)
                }

            finish()
        }
    }

    private fun initView() {
        onBoardingTitle.text = titleList[0]
        onBoardingSubTitle.text = subTitleList[0]
        imageSliderAdapter = ImageSliderAdapter(this@OnBoardingActivity)
        onBoardingViewPager.adapter = imageSliderAdapter
    }

    private fun setUpIndicator() {
        onBoardingIndicator.setViewPager(onBoardingViewPager)
    }
}
