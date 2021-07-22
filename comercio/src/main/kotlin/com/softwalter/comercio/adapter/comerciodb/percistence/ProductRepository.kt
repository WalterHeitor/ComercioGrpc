package com.softwalter.comercio.adapter.comerciodb.percistence

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.softwalter.comercio.adapter.comerciodb.entity.ProductEntity
import org.slf4j.LoggerFactory

class ProductRepository(
    private val dynamoDBMapper: DynamoDBMapper
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun create(product: ProductEntity) : ProductEntity{
        try {
            dynamoDBMapper.save(product)
            return product
        }catch (e: Exception){
            logger.error("Erro ao criar o produto : ${e.message}")
            throw  e
        }
    }

}