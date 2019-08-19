package com.lambdaschool.sprint2_challenge.model

data class GroceryItems (
    val kind: String,
    val resourceId: Int,
    var ordered: Boolean = false
)