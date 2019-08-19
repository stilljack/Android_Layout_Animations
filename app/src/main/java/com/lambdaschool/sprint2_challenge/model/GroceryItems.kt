package com.lambdaschool.sprint2_challenge.model

import java.io.Serializable

data class GroceryItems (
    val kind: String,
    val resourceId: Int,
    var ordered: Boolean = false
) :Serializable