package com.softwalter.comercio.adapter.comerciodb.config.moviesdb

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.slf4j.Logger
import java.io.FileInputStream

class MoviesLoadData {
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
    //val parser: JsonParser = JsonFactory().createParser(File("moviedata.json"))
    val parser: FileInputStream = FileInputStream("./moviedata.json")
    val rootNode: JsonNode = ObjectMapper().readTree(parser)
    val iter: MutableIterator<JsonNode> = rootNode.iterator()

    var currentNode: ObjectNode

    while (iter.hasNext()){
        currentNode = iter.next() as ObjectNode
        val year: Int = currentNode.path("year").asInt()
        val title: String = currentNode.path("title").asText()
        try {
            logger.info("Deletando o item ... com a condição desejada")
            table.putItem(Item().withPrimaryKey("year", year, "title", title)
                .withJSON("info", currentNode.path("info").toString()))
            logger.info("Deletado com sucesso ...")
        }
        //        catch (e: Exception){
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
    parser.close()

}