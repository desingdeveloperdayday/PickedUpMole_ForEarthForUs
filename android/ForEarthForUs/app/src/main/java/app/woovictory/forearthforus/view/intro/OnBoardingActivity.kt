package app.woovictory.forearthforus.view.intro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import kotlinx.android.synthetic.main.activity_on_boarding.*
import org.jetbrains.anko.startActivity

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
            }

        })

        onBoardingSkipButton.setOnClickListener {
            Intent(this, MainActivity::class.java)
                .apply {
                    addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                }.let {
                    startActivity(it)
                }

            finish()
            /*startActivity<MainActivity>()
            finish()*/
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
