package com.softwalter.comercio.adapter.endpoint

import com.softwalter.comercio.ComercioServiceGrpcKt
import com.softwalter.comercio.ProductRequest
import com.softwalter.comercio.ProductResponse
import javax.inject.Singleton

@Singleton
class ProductEndpoint(

): ComercioServiceGrpcKt.ComercioServiceCoroutineImplBase() {
    override suspend fun create(request: ProductRequest): ProductResponse {
        return super.create(request)
    }
}