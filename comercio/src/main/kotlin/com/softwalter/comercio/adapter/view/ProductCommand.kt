package com.softwalter.comercio.adapter.view

import com.softwalter.comercio.adapter.model.Product
import io.micronaut.core.annotation.Introspected

@Introspected
data class ProductCommand(
    val nome: String,
    val productBrand :String,
    val outfitter :String,
    val price :Double,
    val salePrice :Double,
) {
    fun productCommandToProduct() = Product(
        nome = this.nome,
        productBrand = this.productBrand,
        outfitter = this.outfitter,
        price = this.price,
        salePrice = this.salePrice)
}
