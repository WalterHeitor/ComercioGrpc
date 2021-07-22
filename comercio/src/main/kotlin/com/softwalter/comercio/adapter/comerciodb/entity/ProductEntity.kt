package com.softwalter.comercio.adapter.comerciodb.entity

import com.amazonaws.services.dynamodbv2.datamodeling.*

@DynamoDBTable(tableName = "softwalter_comercio")
class ProductEntity {

    companion object{
        const val productPrefix = "PROD"
    }

    @DynamoDBHashKey(attributeName = "cod_chav_cslt")
    var productPK = "PRODUCT"
    @DynamoDBRangeKey(attributeName = "cod_chav_filg")
    var productId = ""
        set(value) {
            field = if (value.contains("${productPrefix}#")) value else "$productPrefix#$value"
        }
    @DynamoDBAttribute(attributeName = "")
    var name = ""

//    @DynamoDBIgnore
//    fun getProductIdValue() = getOverloadedFieldValue(productId)
}