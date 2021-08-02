package com.softwalter.comercio.adapter.comerciodb.config.moviesdb

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap
import com.amazonaws.services.dynamodbv2.model.ReturnValue
import org.slf4j.Logger

class MoviesItemOps03 {
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
        .withUpdateExpression("set info.rating = :r, info.pilot=:p, info.actors=:a")
        .withValueMap(ValueMap()
            .withNumber(":r", 5.5)
            .withString(":p", "Everything happens all at once.")
            .withList(":a", listOf("Larry", "Moe", "Curly")))
        .withReturnValues(ReturnValue.UPDATED_NEW)

    try {
        logger.info("Atualizando o item ...")
        val outcome: UpdateItemOutcome = table.updateItem(updateItemSpec)
        logger.info("Atualizado com sucesso ... : ${outcome.item.toJSONPretty()}")
    }
    //    catch (e: Exception){
//        logger.info("Erro ao cria a Tabela ...")
//        logger.info("Menssagem: ${e.message}")
//    }
    catch (ase: AmazonServiceException, ){
        logger.error("Operacao nao completada")
        logger.error("Menssagem: ${ase.message}")
        logger.error("Mensagem de erro: ${ase.statusCode}")
        logger.error("Status HTTP: ${ase.errorCode}")
        logger.error("Erro codigo AWS: ${ase.errorType}")
        logger.error("ID da requiscao: ${ase.errorMessage}")
    }
    catch (ace: AmazonClientException){
        logger.error("Erro interno ocorrido na comunicação do DynamoDB")
        logger.error("Mensagem de erro ${ace.message}")
        logger.error("Mensagem de erro local ${ace.localizedMessage.toString()}")
        logger.error("Mensagem de causa de erro ${ace.cause}")
        logger.error("Mensagem de erro stack trace ${ace.stackTrace}")
    }



}