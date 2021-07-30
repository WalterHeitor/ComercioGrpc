package com.softwalter.comercio.adapter.comerciodb.config.table

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome
import com.amazonaws.services.dynamodbv2.document.Table
import org.slf4j.Logger
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class MoviesItemOps01 {
}

fun main(){
    /**
     * encapsulando o metodo tableInstace() na classe
     * MoviesCreateTable.kt
     * para usa-lo em demais lugares
     */
    val (logger: Logger, dynamoDB: DynamoDB) = tableInstance()

    val table: Table = dynamoDB.getTable("Movies")
    val year: Int = 2021
    val title: String = "Poeira em alto mar"

    val infoMap = hashMapOf(
        "plot" to  "Not hasppens at all", "rating" to  0)
    try {
        logger.info("Adicionado um novo item")
        val outcome: PutItemOutcome = table
            .putItem(Item().withPrimaryKey("year",year,
            "title", title).withMap("info", infoMap))
        logger.info("Item adicionado com SUCESSO!!! ${outcome.putItemResult}")
    }catch (e: Exception){
        logger.error("Erro na tentaticva de adicionar o item")
        logger.error("Menssagem: ${e.message}")
    }

}