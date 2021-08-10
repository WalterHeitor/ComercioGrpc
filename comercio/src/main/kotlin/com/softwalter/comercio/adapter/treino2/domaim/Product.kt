package com.softwalter.comercio.adapter.treino2.domaim


import com.softwalter.comercio.ProductResponse
import com.sofwalter.adapter.db.ProductEntidade

class Product(
        val productId: String,
        val name: String,
        val productBrand: String, // marca do produto
        val outfitter: String,     // fornecedor
        val price: Double,         // preço
        val salePrice: Double,   //preço de venda
) {
    fun productToResponse(): ProductResponse = ProductResponse
            .newBuilder()
            .setName(productId)
            .setName(name)
            .setProductBrand(productBrand)
            .setOutfitter(outfitter)
            .setPrice(price)
            .setSalePrice(salePrice)
            .build()
    fun productToEntidade(): ProductEntidade {
        return ProductEntidade(
            productId,
            name,
            productBrand,
            outfitter,
            price,
            salePrice
        )
    }
}
