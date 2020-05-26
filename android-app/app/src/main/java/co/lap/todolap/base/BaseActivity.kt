package co.lap.todolap.base

import androidx.appcompat.app.AppCompatActivity
import co.lap.todolap.R
import co.lap.todolap.common.DialogFabric
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener

abstract class BaseActivity : AppCompatActivity() {
    private val TAG = "BaseActivity"

    /**
     * Remember implements PermissionListener in activity that calls this function
     */
    fun getPermission(permission: String, permissionDescription: String, permissionListener: PermissionListener) {
        DialogFabric()
            .getDialog(applicationContext, DialogFabric.TYPE_PERMISSION, permission, permissionDescription)
            .setPositiveButton(resources.getString(R.string.acceptPermission)) { dialog, which ->
                Dexter.withContext(applicationContext)
                    .withPermission(permission)
                    .withListener(permissionListener)
                    .check()
            }
            .setNegativeButton(resources.getString(R.string.declinePermission)) { dialog, which ->

            }
    }

    fun showMaterialDialog(type: String, message: String) {
        DialogFabric().getDialog(this, type, message, "").show()
    }
}