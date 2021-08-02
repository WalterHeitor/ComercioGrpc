package com.softwalter.comercio.adapter.model

import com.softwalter.comercio.adapter.view.ProductCommand

class Product(
    val nome: String,
    val productBrand :String,
    val outfitter :String,
    val price :Double,
    val salePrice :Double,
) {
    fun productCommandToProduct(productCommand: ProductCommand) = Product(
        nome = this.nome,
        productBrand = this.productBrand,
        outfitter = this.outfitter,
        price = this.price,
        salePrice = this.salePrice)

}