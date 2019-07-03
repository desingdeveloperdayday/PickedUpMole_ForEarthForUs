package app.woovictory.forearthforus.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import app.woovictory.forearthforus.MainActivity
import app.woovictory.forearthforus.R
import kotlinx.android.synthetic.main.custom_dialog.*

/**
 * Created by VictoryWoo
 * TODO : DataBinding 으로 변경하기.
 */
class CustomDialog(context: Context) : Dialog(context), View.OnClickListener {

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
                /*val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)*/
            }
        }
    }
}