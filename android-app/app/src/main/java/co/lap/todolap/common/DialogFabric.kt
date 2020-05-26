package co.lap.todolap.common

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogFabric {
    companion object {
        const val TYPE_ERROR = "error"
        const val TYPE_SUCCESS = "success"
        const val TYPE_PERMISSION = "permission"
    }

    fun getDialog(context: Context, type: String, message: String, permission: String) : MaterialAlertDialogBuilder {
        when(type) {
            TYPE_ERROR -> {
                return MaterialAlertDialogBuilder(context)
                    .setMessage(message)
                    .setTitle("Error!")
            }
            TYPE_SUCCESS -> {
                return MaterialAlertDialogBuilder(context)
                    .setMessage(message)
                    .setTitle("Listo!")
            }
            TYPE_PERMISSION -> {
                return MaterialAlertDialogBuilder(context)
                    .setTitle("Use ${permission}?")
                    .setMessage(message)
            }
        }
        return MaterialAlertDialogBuilder(context)
            .setMessage("Necesitas añadir el tipo")
    }
}