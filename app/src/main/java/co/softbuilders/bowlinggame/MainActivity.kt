package co.softbuilders.bowlinggame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import co.softbuilders.bowlinggame.dialog.AddFrameDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import BowlingEngine
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var data : MutableList<BowlingEngine.FrameResult> = mutableListOf()
    private var mAdapter: FrameAdapter? = null
    val  bowlingGame = BowlingEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initUI()
    }

    private fun initUI() {
        fab.setOnClickListener { view ->
            if(mAdapter!!.getItems()!!.size<10){
                val dialog = AddFrameDialog(this).show()
                dialog.setOnClickListner(object : AddFrameDialog.OnClickListener{
                    override fun onCancel() {
                    }

                    override fun onConfirm(frame: BowlingEngine.FrameResult) {
                        emptyFrame.visibility = View.GONE
                        recycler_frames.visibility = View.VISIBLE
                        mAdapter!!.addItem(frame)
                    }
                })
            }
        }


        mAdapter = FrameAdapter(
            data,
            this as AppCompatActivity
        )

        val mLayoutManager = LinearLayoutManager(this)
        recycler_frames.apply {
            layoutManager = mLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        if(data.isEmpty()){
            emptyFrame.visibility = View.VISIBLE
            recycler_frames.visibility = View.GONE
        }else{
            emptyFrame.visibility = View.GONE
            recycler_frames.visibility = View.VISIBLE
        }

        compute.setOnClickListener {
            if(mAdapter!!.getItems()!!.size<10){
                Toast.makeText(this,"You must add 10 frames to compute the Game",Toast.LENGTH_LONG).show()
            }else if (mAdapter!!.getItems()!!.size==10){
                emptyFrame.visibility = View.VISIBLE
                recycler_frames.visibility = View.GONE
                emptyFrame.text = bowlingGame.computeGame(mAdapter!!.getItems()!!)
            }
        }

        reset.setOnClickListener {
            mAdapter!!.setItems(mutableListOf())
            emptyFrame.visibility = View.VISIBLE
            recycler_frames.visibility = View.GONE
            emptyFrame.text = "Game reset\n You can play another game\n Click on the fab button to start"
        }

        //add a button to compute everything and to reset everything
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
