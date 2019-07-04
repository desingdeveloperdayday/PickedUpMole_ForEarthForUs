package app.woovictory.forearthforus.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import app.woovictory.forearthforus.util.glide.GlideApp
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
/*    private val bind = CustomDialogBinding.inflate(LayoutInflater.from(context))
    private lateinit var title: String
    private lateinit var content: String*/

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
        GlideApp.with(context)
            .load(missionCategoryList[categoryId - 1])
            .into(dialogImage)
    }

    /*   class CustomBuilder(context: Context) {
           private val dialog = CustomDialog(context)

           fun setTitle(title: String): CustomBuilder {
               dialog.title = title
               return this
           }

           fun setContent(content: String): CustomBuilder {
               dialog.content = content
               return this
           }

           fun show(): CustomDialog {
               dialog.show()
               return dialog
           }
       }
   */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialogOkayButton -> {
                dismiss()
                SharedPreferenceManager.missionCompleteStatus = false
                SharedPreferenceManager.missionCompleteCount--
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
            }
        }
    }
}