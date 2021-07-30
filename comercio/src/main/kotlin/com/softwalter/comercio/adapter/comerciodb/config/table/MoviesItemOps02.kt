package com.softwalter.comercio.adapter.comerciodb.config.table

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec
import org.slf4j.Logger

class MoviesItemOps02 {

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

    var spec: GetItemSpec = GetItemSpec().withPrimaryKey(
        "year", year, "title", title)
    try {
        logger.info("Tentando ler o item ...")
        val outcome: Item = table.getItem(spec)
        logger.info("Sucesso na leitura do item ... : $outcome ")
    }catch (e: Exception){
        logger.error("Incapaz de ler o item: $year, $title ")
        logger.error("Messagem : ${e.message}")
    }

}