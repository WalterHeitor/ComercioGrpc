package com.softwalter.comercio.adapter.treino2.input


import com.softwalter.comercio.ProductRequest
import com.sofwalter.adapter.db.ProductEntidade
import com.softwalter.comercio.adapter.treino2.db.ProductRepository
import com.softwalter.comercio.adapter.treino2.domaim.Product
import com.sofwalter.adapter.service.ProductGateway
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ProductGatewayImpl(
        val productRepository: ProductRepository
) :ProductGateway{
    val logger = LoggerFactory.getLogger(this::class.java)
    override fun productView(request: ProductRequest): Product {
        val product = Product(
                request.productId,
                request.name,
                request.productBrand,
                request.outfitter,
                request.price,
                request.salePrice
        )
        logger.info("product $product")
        val entity = product.productToEntidade()
        return product
    }
    override fun productToEntidade(product: Product) = ProductEntidade(
            product.productId,
            product.name,
            product.productBrand,
            product.outfitter,
            product.price,
            product.salePrice
    )
    override fun requestToEntidade(request: ProductRequest): Product {
        val productEntidade = ProductEntidade(
                request.productId,
                request.name,
                request.productBrand,
                request.outfitter,
                request.price,
                request.salePrice
        )
        logger.info("productEntidade $productEntidade")
        return productRepository.salveProduct(productEntidade)
    }
}