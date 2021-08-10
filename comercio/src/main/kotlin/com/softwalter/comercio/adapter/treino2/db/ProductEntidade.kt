package com.sofwalter.adapter.db

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.softwalter.comercio.adapter.treino2.domaim.Product

@DynamoDBTable(tableName = "vendas")
data class ProductEntidade(
        @DynamoDBHashKey(attributeName = "PK")
//        @DynamoDBAutoGeneratedKey
        var product_id: String?=null,
        @DynamoDBRangeKey(attributeName = "SK")
        var nome: String?=null,
        @DynamoDBAttribute
        var product_brand: String,
        @DynamoDBAttribute
        var outfitter: String,
        @DynamoDBAttribute
        val price: Double,
        @DynamoDBAttribute
        val salePrice: Double,

        ) {
        fun toProduct() = Product(
                product_id!!,
                nome!!,
                product_brand,
                outfitter,
                price,
                salePrice

        )
}
