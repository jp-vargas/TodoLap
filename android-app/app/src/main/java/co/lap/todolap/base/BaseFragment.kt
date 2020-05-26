package co.lap.todolap.base

import androidx.fragment.app.Fragment
import co.lap.todolap.common.DialogFabric

abstract class BaseFragment : Fragment() {
    fun showMaterialDialog(type: String, message: String) {
        context?.let {
            DialogFabric().getDialog(it, type, message, "").show()
        }
    }
}