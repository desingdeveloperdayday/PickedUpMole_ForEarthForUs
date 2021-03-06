package app.woovictory.forearthforus.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import app.woovictory.forearthforus.view.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.utils.extensions.loadDrawableImage
import kotlinx.android.synthetic.main.custom_dialog.*

/**
 * Created by VictoryWoo
 * TODO : DataBinding 으로 변경하기.
 */
class CustomDialog(
    context: Context, val categoryId: Int,
    val title: String, val completeMessage: String
) : Dialog(context), View.OnClickListener {

    private val layout = R.layout.custom_dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        init()

    }

    private fun init() {
        dialogOkayButton.setOnClickListener(this)
        setUpDialog(categoryId)
    }

    private fun setUpDialog(categoryId: Int) {
        dialogText.text = title
        dialogContent.text = completeMessage
        dialogImage.loadDrawableImage(missionCategoryList[categoryId - 1])
        //loadDrawableImage(dialogImage, missionCategoryList[categoryId - 1])
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialogOkayButton -> {
                dismiss()
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
            }
        }
    }
}