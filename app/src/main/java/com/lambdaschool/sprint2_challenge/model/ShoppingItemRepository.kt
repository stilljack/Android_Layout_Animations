package com.lambdaschool.sprint2_challenge.model

class ShoppingItemRepository {
    companion object {
        var shoppingList = mutableListOf<GroceryItems>()
        fun createShoppingList() {
            for (i in 0 until ShoppingItemConstants.ICON_IDS.size) {
                shoppingList.add(GroceryItems(ShoppingItemConstants.ITEM_NAMES_RAW[i], ShoppingItemConstants.ICON_IDS[i], false))
            }
        }
    }
}