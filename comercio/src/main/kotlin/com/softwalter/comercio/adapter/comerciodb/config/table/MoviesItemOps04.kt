package com.softwalter.comercio.adapter.comerciodb.config.table

import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap
import com.amazonaws.services.dynamodbv2.model.ReturnValue
import org.slf4j.Logger

class MoviesItemOps04 {
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

    val updateItemSpec: UpdateItemSpec = UpdateItemSpec().withPrimaryKey(
        "year", year, "title", title)
        .withUpdateExpression("set info.rating = info.rating + :val")
        .withValueMap(ValueMap()
            .withNumber(":val", 1))
        .withReturnValues(ReturnValue.UPDATED_NEW)

    try {
        logger.info("Atualizando o item ...")
        val outcome: UpdateItemOutcome = table.updateItem(updateItemSpec)
        logger.info("Atualizado com sucesso ... : ${outcome.item.toJSONPretty()}")
    }catch (e: Exception){
        logger.error("Incapaz de ler o item: $year, $title ")
        logger.error("Messagem : ${e.message}")
    }


}