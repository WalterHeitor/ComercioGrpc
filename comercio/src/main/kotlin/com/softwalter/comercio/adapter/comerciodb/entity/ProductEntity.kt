package com.softwalter.comercio.adapter.comerciodb.entity

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.softwalter.comercio.adapter.model.Product
import java.util.*

@DynamoDBTable(tableName = "softwalter_comercio")
data class ProductEntity(
    @DynamoDBHashKey(attributeName = "PK")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.N)
    var id: UUID? = UUID.randomUUID(),
    @DynamoDBAttribute(attributeName = "SK")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.N)
    var name: String,
    @DynamoDBAttribute(attributeName = "")
    var product_brand: String,
    @DynamoDBAttribute(attributeName = "")
    var outfitter: String,
    @DynamoDBAttribute(attributeName = "")
    var price: Double,
    @DynamoDBAttribute(attributeName = "")
    var sale_price: Double
){
    fun modelToEntity(p: Product) = ProductEntity(
        id = UUID.randomUUID(),
        name = p.nome,
        product_brand = p.productBrand,
        outfitter = p.outfitter,
        price = p.price,
        sale_price = p.salePrice,
    )
}