package com.softwalter.comercio.adapter.service.implements

import com.softwalter.comercio.adapter.comerciodb.percistence.ProductRepository
import com.softwalter.comercio.adapter.model.Product
import com.softwalter.comercio.adapter.service.ProductService
import com.softwalter.comercio.adapter.view.ProductCommand
import javax.inject.Singleton

@Singleton
class ProductSeviceImpl(val productRepository: ProductRepository) : ProductService {
    override suspend fun save(productCommand: ProductCommand): Product{
        val product = productCommand.productCommandToProduct()
        productRepository.create(product)
        return product
    }

}