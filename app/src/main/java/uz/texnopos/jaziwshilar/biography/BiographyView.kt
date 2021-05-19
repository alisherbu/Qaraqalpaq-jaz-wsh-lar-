package uz.texnopos.jaziwshilar.biography

interface BiographyView {
    fun getBiography(biography: String, poetName: String, lifeSpan: String)
    fun changeBookmark(isPressed: Boolean)
}