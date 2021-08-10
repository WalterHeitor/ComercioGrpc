package com.softwalter.comercio.adapter.treino01.moviesdb

import com.amazonaws.AmazonClientException
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap
import org.slf4j.Logger

class MoviesItemOps06 {
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
    /**
     * Faz update no banco de acordo com a condicao.
     */
    val deleteItemSpec: DeleteItemSpec = DeleteItemSpec().withPrimaryKey(
        "year", year, "title", title)
        .withConditionExpression("info.rating >= :val")
        .withValueMap(ValueMap()
            .withNumber(":val", 5.0))

    try {
        logger.info("Deletando o item ... com a condição desejada")
        table.deleteItem(deleteItemSpec)
        logger.info("Deletado com sucesso ...")
    }
//        catch (e: Exception){
//        logger.info("Erro ao cria a Tabela ...")
//        logger.info("Menssagem: ${e.message}")
//    }
//    catch (ase: AmazonServiceException, ){
//        logger.error("Operacao nao completada")
//        logger.error("Menssagem: ${ase.message}")
//        logger.error("Mensagem de erro: ${ase.statusCode}")
//        logger.error("Status HTTP: ${ase.errorCode}")
//        logger.error("Erro codigo AWS: ${ase.errorType}")
//        logger.error("ID da requiscao: ${ase.errorMessage}")
//    }
    catch (ace: AmazonClientException){
        logger.error("Erro interno ocorrido na comunicação do DynamoDB")
        logger.error("Mensagem de erro ${ace.message}")
        logger.error("Mensagem de erro local ${ace.localizedMessage.toString()}")
        logger.error("Mensagem de causa de erro ${ace.cause}")
        logger.error("Mensagem de erro stack trace ${ace.stackTrace}")
    }



}