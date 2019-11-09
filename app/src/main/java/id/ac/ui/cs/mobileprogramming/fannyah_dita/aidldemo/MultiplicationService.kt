package id.ac.ui.cs.mobileprogramming.fannyah_dita.aidldemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException

class MultiplicationService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    val binder = object : MultiplyInterface.Stub() {

        @Throws(RemoteException::class)
        override fun multiply(firstNum: Int, secondNum: Int): Int {
            return firstNum * secondNum
        }
    }
}
