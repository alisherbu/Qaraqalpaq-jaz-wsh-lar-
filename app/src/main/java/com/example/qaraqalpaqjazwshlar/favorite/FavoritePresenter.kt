import android.content.Intent
import com.example.qaraqalpaqjazwshlar.biography.BioActivity
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import com.example.qaraqalpaqjazwshlar.favorite.FavoriteView
import com.example.qaraqalpaqjazwshlar.favorite.FragmentFavorite

class FavoritePresenter(private var fragment: FragmentFavorite, var view: FavoriteView) {
    private var dao: PoetsDao = PoetsDatabase.getInstance(fragment.requireContext()).dao()
    private var poetList = dao.getAllFavorites()

    fun getAllFavorites() {
        view.setData(poetList)
    }


    fun startActivity() {
        val intent = Intent(fragment.requireContext(), BioActivity::class.java)
        fragment.adapter.setOnItemClickListener { _, _, poetName ->
            val id = dao.getIdByPoetName(poetName)
            intent.putExtra("id", id)
            fragment.startActivity(intent)
        }
    }


}