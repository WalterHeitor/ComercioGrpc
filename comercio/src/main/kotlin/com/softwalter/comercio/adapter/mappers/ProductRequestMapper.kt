package com.softwalter.comercio.adapter.mappers

import com.softwalter.comercio.ProductRequest
import com.softwalter.comercio.adapter.view.ProductCommand

object ProductRequestMapper {
    fun fromProductRequestToProductCommand(from: ProductRequest) = ProductCommand(
        nome = from.name,
        productBrand = from.productBrand,
        outfitter = from.outfitter,
        price = from.price.toDouble(),
        salePrice = from.salePrice.toDouble()
    )
}