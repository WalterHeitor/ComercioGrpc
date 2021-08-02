package com.softwalter.comercio.adapter.endpoint

import com.softwalter.comercio.ComercioServiceGrpcKt
import com.softwalter.comercio.ProductRequest
import com.softwalter.comercio.ProductResponse
import com.softwalter.comercio.adapter.mappers.ProductRequestMapper
import com.softwalter.comercio.adapter.service.ProductService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductEndpoint(
    @Inject val productService: ProductService,
): ComercioServiceGrpcKt.ComercioServiceCoroutineImplBase() {
    override suspend fun create(request: ProductRequest): ProductResponse {
        productService.save(ProductRequestMapper.fromProductRequestToProductCommand(request))
        return super.create(request)
    }
}