package id.ac.ui.cs.mobileprogramming.fannyah_dita.aidldemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var multiplyInterface: MultiplyInterface
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            multiplyInterface = MultiplyInterface.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val firstNum = Integer.parseInt(first_num_edittext.text.toString())
            val secondNum = Integer.parseInt(second_num_edittext.text.toString())

            try {
                val result = multiplyInterface.multiply(firstNum, secondNum)
                result_text.text = "$firstNum * $secondNum = $result"
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        val intent = Intent(this@MainActivity, MultiplicationService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }
}
