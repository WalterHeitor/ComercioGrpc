package com.softwalter.comercio.adapter.treino2

import com.softwalter.comercio.ComercioServiceGrpcKt
import com.softwalter.comercio.ProductRequest
import com.softwalter.comercio.ProductResponse
import com.sofwalter.adapter.service.ProductGateway
import javax.inject.Singleton

@Singleton
class ProductEndpoint(
    private val productGateway: ProductGateway,
): ComercioServiceGrpcKt.ComercioServiceCoroutineImplBase() {
    override suspend fun create(request: ProductRequest): ProductResponse {
        val product = productGateway.requestToEntidade(request)
        return product.productToResponse()
    }
}