package com.softwalter.comercio.adapter.service

import com.softwalter.comercio.adapter.model.Product
import com.softwalter.comercio.adapter.view.ProductCommand
import javax.inject.Singleton

@Singleton
interface ProductService {
    suspend fun save(productCommand: ProductCommand): Product
}
