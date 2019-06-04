package app.woovictory.forearthforus

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_test2.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        val uri = Uri.parse("http://looksgoood.pythonanywhere.com/media/images/earth/earth_1.svg")
        GlideToVectorYou.justLoadImage(this, uri, imaged)
    }
}
