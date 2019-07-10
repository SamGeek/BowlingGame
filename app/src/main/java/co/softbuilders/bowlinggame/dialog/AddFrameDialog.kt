package co.softbuilders.bowlinggame.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import co.softbuilders.bowlinggame.R

/**
 * @Doc $doc
 */

class AddFrameDialog(ctx: Context) : View.OnClickListener {

    private var dialog: AlertDialog? = null
    private lateinit var v: View
    internal var frameTypeRG: RadioGroup? = null
    internal var normalRadio: RadioButton? = null
    internal var lastRadio: RadioButton? = null
    internal var firstTrySpinner: Spinner? = null
    internal var secondTrySpinner: Spinner? = null
    internal var thirdTrySpinner: Spinner? = null
    internal var thirdContainer: LinearLayout? = null
    private var mContext: Context? = null
    private var frame: BowlingEngine.FrameResult? = null

    init {
        if (dialog == null) {
            mContext = ctx
            v = LayoutInflater.from(ctx).inflate(R.layout.dialog_add_frame, null)
            dialog = AlertDialog.Builder(ctx).setView(v).create()
            frameTypeRG = v.findViewById<RadioGroup>(R.id.frameTypeRG)
            normalRadio = v.findViewById<RadioButton>(R.id.normalRadio)
            lastRadio = v.findViewById<RadioButton>(R.id.lastRadio)
            firstTrySpinner = v.findViewById<Spinner>(R.id.firstTrySpinner)
            secondTrySpinner = v.findViewById<Spinner>(R.id.secondTrySpinner)
            thirdTrySpinner = v.findViewById<Spinner>(R.id.thirdTrySpinner)
            thirdContainer = v.findViewById<LinearLayout>(R.id.thirdContainer)
            v.findViewById<View>(R.id.close_dialog).setOnClickListener(this)
            v.findViewById<View>(R.id.btn_validate).setOnClickListener(this)
            dialog!!.setCancelable(false)


            frameTypeRG!!.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { group, checkedId ->
                    if(checkedId == R.id.normalRadio){
                        thirdContainer!!.visibility = View.GONE
                    }else{
                        thirdContainer!!.visibility = View.VISIBLE
                    }
                })
        }
    }


    fun show(): AddFrameDialog {
        if (dialog != null) {
            dialog!!.show()
        }
        return this
    }

    fun dismiss(): AddFrameDialog {
        if (dialog != null) {
            dialog!!.dismiss()
        }
        return this
    }

    private lateinit var onClickListener: OnClickListener

    fun setOnClickListner(onClickListner: OnClickListener) {
        this.onClickListener = onClickListner
    }


    override fun onClick(view: View) {

        when (view.id) {
            R.id.close_dialog ->  dialog!!.dismiss()
            R.id.btn_validate -> {
                //retrieve frame result and send to the activity trough callbacks
                if(normalRadio!!.isChecked){
                    if (secondTrySpinner!!.selectedItem.toString().trim().equals("SPARE")){
                        frame = BowlingEngine.FrameResult(mutableListOf(firstTrySpinner!!.selectedItem.toString().trim(),"SPARE"))
                    }else if (firstTrySpinner!!.selectedItem.toString().trim().equals("STRIKE")){
                        frame = BowlingEngine.FrameResult(mutableListOf("STRIKE"))
                    }else{
                        frame = BowlingEngine.FrameResult(mutableListOf(firstTrySpinner!!.selectedItem.toString().trim(),
                            secondTrySpinner!!.selectedItem.toString().trim()))
                    }
                }else{

                    frame = BowlingEngine.FrameResult(mutableListOf(firstTrySpinner!!.selectedItem.toString().trim(),
                        secondTrySpinner!!.selectedItem.toString().trim(),
                        thirdTrySpinner!!.selectedItem.toString().trim()
                        ))
                }

                //listener action
                onClickListener.onConfirm(frame!!)
                dialog!!.dismiss()
            }
            else -> {
            }
        }
    }

    interface OnClickListener {
        fun onCancel()
        fun onConfirm(frame : BowlingEngine.FrameResult)
    }

    companion object {

        fun Builder(ctx: Context): AddFrameDialog {
            return AddFrameDialog(ctx)
        }
    }
}
