package co.softbuilders.bowlinggame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class FrameAdapter(
    private var frames: MutableList<BowlingEngine.FrameResult>?,
    var mActivity: AppCompatActivity
) : RecyclerView.Adapter<FrameAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val context: Context, view: View) : RecyclerView.ViewHolder(view){
        var first: TextView
        var second: TextView
        var third: TextView

        init {
            first = view.findViewById<View>(R.id.first) as TextView
            second = view.findViewById<View>(R.id.second) as TextView
            third = view.findViewById<View>(R.id.third) as TextView
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.frame_item, parent, false)
        return MyViewHolder(mActivity, itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val frame = frames!![position]

        if(frame.values.size==1){
            holder.first.text = frame.values[0]
            holder.second.visibility = View.GONE
            holder.third.visibility = View.GONE
        }else if (frame.values.size==2){
            holder.first.text = frame.values[0]
            holder.second.visibility = View.VISIBLE
            holder.second.text = frame.values[1]
        }else if (frame.values.size==3) {
            holder.third.visibility = View.VISIBLE
            holder.first.text = frame.values[0]
            holder.second.text = frame.values[1]
            holder.third.text = frame.values[2]
        }

        /*if(remoteService.isPercent){
            holder.operatorName.text =   "${remoteService.name}  - P2P   ${remoteService.discount} %"
        }else {
            holder.operatorName.text =   "${remoteService.name}  - P2P"
        }*/
    }

    override fun getItemCount(): Int {
        return frames!!.size
    }

    fun setItems(data: MutableList<BowlingEngine.FrameResult>?): Unit {
        this.frames = data
        notifyDataSetChanged()
    }

    fun getItems(): MutableList<BowlingEngine.FrameResult>? {
        return  this.frames
    }

    fun addItem(data: BowlingEngine.FrameResult): Unit {
        this.frames!!.add(data)
        notifyDataSetChanged()
    }

    companion object {

        private val TAG = "ProviderAdapter"
    }

}
