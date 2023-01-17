package uz.texnopos.jaziwshilar.poets

import uz.texnopos.jaziwshilar.data.Poets
import uz.texnopos.jaziwshilar.data.PoetsDao
import java.util.*
import kotlin.collections.ArrayList

class PoetPresenter(private var dao: PoetsDao, var view: PoetView) {
    private lateinit var poetList: List<Poets>
    fun getAllPoets() {
        poetList = dao.getAllPoetsAndId()
        view.setData(poetList)
    }

    fun filter(text: String) {
        val filteredNames = ArrayList<Poets>()
        poetList.filterTo(filteredNames) {
            it.poetName!!.toLowerCase(Locale.ROOT).contains(text.toKiril().toLowerCase(Locale.ROOT))
        }
        view.filteredNames(filteredNames)
    }

    private fun String.toKiril(): String {
        var s = ""
        for (i in this) {
            when (i) {
                'A', 'a' -> s += 'а'
                'B', 'b' -> s += 'б'
                'С', 'c' -> s += 'ц'
                'D', 'd' -> s += 'д'
                'E', 'e' -> s += 'е'
                'F', 'f' -> s += 'ф'
                'G', 'g' -> s += 'г'
                'H', 'h' -> s += 'ҳ'
                'I', 'i' -> s += 'и'
                'J', 'j' -> s += 'ж'
                'K', 'k' -> s += 'к'
                'L', 'l' -> s += 'л'
                'M', 'm' -> s += 'м'
                'N', 'n' -> s += 'н'
                'O', 'o' -> s += 'о'
                'P', 'p' -> s += 'п'
                'Q', 'q' -> s += 'қ'
                'R', 'r' -> s += 'р'
                'S', 's' -> s += 'с'
                'T', 't' -> s += 'т'
                'U', 'u' -> s += 'у'
                'V', 'v' -> s += 'в'
                'W', 'w' -> s += 'ў'
                'X', 'x' -> s += 'х'
                'Y', 'y' -> s += 'й'
                'Z', 'z' -> s += 'з'
                'Á', 'á' -> s += 'ә'
                'Ǵ', 'ǵ' -> s += 'ғ'
                'Í', 'ı' -> s += 'ы'
                'Ó', 'ó' -> s += 'ө'
                'Ń', 'ń' -> s += 'ӊ'
                'Ú', 'ú' -> s += 'ү'
                else -> s += i
            }
        }
        if (s.contains("sh")) s.replace("sh", "ш")
        if (s.contains("Sh")) s.replace("Sh", "ш")
        return s
    }
}
