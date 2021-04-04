package net.leopisang.notificationlog.component

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import net.leopisang.notificationlog.R

/**
 * Component Class for Loading Dialog Spinner
 *
 * @param context Current Context, will be used on [AlertDialog.Builder]
 * @param layoutInflater Inflater to create [View] that will be used on [AlertDialog.Builder.setView]
 * @author LeoPisanGG
 *         Created on 04/04/2021 05:23.
 *
 */
class LoadingDialog(val context: Context, val layoutInflater: LayoutInflater) {

    private lateinit var alertBuilder: AlertDialog.Builder

    private fun createBuilder(layout: Int) {
        alertBuilder = AlertDialog.Builder(context)
        val customLayout: View =
                layoutInflater.inflate(layout, null)
        alertBuilder.setView(customLayout)
    }

    private fun createBuilder(layout: Int, callback: (View)->Unit) {
        alertBuilder = AlertDialog.Builder(context)
        val customLayout: View =
                layoutInflater.inflate(layout, null)
        alertBuilder.setView(customLayout)
        callback(customLayout)
    }

    /**
     * function to generate AlertDialog object by calling [AlertDialog.create]
     *
     * @return The [AlertDialog] from init function, see [MainLoading]
     *
     */
    fun create() : AlertDialog {
        val alertDialog = alertBuilder.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return alertDialog
    }

    /**
     * function to create Loading Dialog that mainly use in this application
     * (to make the style same on all occassion)
     * by using layout [R.layout.component_main_loading]
     *
     * @return object [LoadingDialog]
     *
     */
    fun MainLoading(): LoadingDialog {
        createBuilder(R.layout.component_main_loading)
        return this
    }

    /**
     * function to create Custom Loading Dialog that you can use with custom layout injected from Activity / Fragment
     * (to make the style same on all occassion)
     * by using layout [R.layout.component_main_loading]
     *
     * @param text String to be showed under [ProgressBar]
     *
     * @return object [LoadingDialog]
     *
     */
    fun MainLoading(text: String): LoadingDialog {
        createBuilder(R.layout.component_main_loading) { customLayout ->
            val textView: TextView = customLayout.findViewById(R.id.text_component_main_loading)
            textView.visibility = View.VISIBLE
        }
        return this
    }

    /**
     * function to create Custom Loading Dialog that you can use with custom layout injected from Activity / Fragment
     * @throws NullPointerException if {@link #create()} is not called
     *
     * @param layout: Integer = resource layout for use in [LayoutInflater.inflate]
     *
     * @return object [LoadingDialog]
     *
     */
    fun CustomLoading(layout: Int): LoadingDialog {
        createBuilder(layout)
        return this
    }
    
}