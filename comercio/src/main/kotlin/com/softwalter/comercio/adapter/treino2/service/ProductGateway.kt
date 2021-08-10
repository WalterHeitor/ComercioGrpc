package com.sofwalter.adapter.service

import com.softwalter.comercio.ProductRequest
import com.sofwalter.adapter.db.ProductEntidade
import com.softwalter.comercio.adapter.treino2.domaim.Product

interface ProductGateway {
 fun productView(request: ProductRequest): Product
 fun productToEntidade(product: Product) : ProductEntidade
 fun requestToEntidade(request: ProductRequest) : Product
}
