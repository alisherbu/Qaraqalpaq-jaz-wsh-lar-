package com.alisherbu.writers.biography

interface BiographyView {
    fun getBiography(biography: String, poetName: String, lifeSpan: String)
    fun changeBookmark(isPressed: Boolean)
}
