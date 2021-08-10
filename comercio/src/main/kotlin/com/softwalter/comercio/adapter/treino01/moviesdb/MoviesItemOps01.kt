package com.softwalter.comercio.adapter.treino01.moviesdb

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome
import com.amazonaws.services.dynamodbv2.document.Table
import org.slf4j.Logger

class MoviesItemOps01 {
}

fun main(){
    /**
     * encapsulando o metodo tableInstace() na classe
     * MoviesCreateTable.kt
     * para usa-lo em demais lugares
     */
    val (logger: Logger, dynamoDB: DynamoDB) = tableInstance()
    /**
     * Encapsulando getTable(dynamoDB) para buscar a tabela
     */
    val table: Table = getTable(dynamoDB)

    /**
     * Enapisulando chave primaria e secundaria
     */
    val (year: Int, title: String) = partitionKeyAndSortKey()

    val infoMap = hashMapOf(
        "plot" to  "Notthing hasppens at all", "rating" to  0)
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

fun partitionKeyAndSortKey(): Pair<Int, String> {
    val year: Int = 2021
    val title: String = "Poeira em alto mar"
    return Pair(year, title)
}

fun getTable(dynamoDB: DynamoDB): Table {
    val tablename = "Movies"
    val table: Table = dynamoDB.getTable(tablename)
    return table
}