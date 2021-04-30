package com.example.qaraqalpaqjazwshlar.biography

import android.content.Intent
import android.os.Build
import android.text.Html
import android.view.MenuItem
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase


@Suppress("DEPRECATION")
class BiographyPresenter(var activity: BioActivity, var view: BiographyView, var id: Int) {
    val dao= PoetsDatabase.getInstance(activity).dao()
     var  menuItem:MenuItem?=null
    val isFavorite=dao.getStatus(id)==1
    fun getBiography(){
        val biography=dao.getBiographyById(id)
        view.getBiography(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(biography, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(biography)
                }.toString(),
                dao.getPoetNameById(id),
                dao.getLifeSpanById(id)
        )
    }
    fun changeBookmark(once: Boolean){
        if (isFavorite){
            menuItem!!.setIcon(R.drawable.ic_baseline_bookmark_24)
            if (once){
                dao.setStatus(id, 0)
                dao.setDate(id, System.currentTimeMillis())
            }
        }
        else {
            menuItem!!.setIcon(R.drawable.ic_baseline_bookmark_border_24)
            if (once) dao.setStatus(id, 1)
        }
    }
    fun share(text: CharSequence){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
       activity.startActivity(Intent.createChooser(sharingIntent, "Share using"))
    }
}