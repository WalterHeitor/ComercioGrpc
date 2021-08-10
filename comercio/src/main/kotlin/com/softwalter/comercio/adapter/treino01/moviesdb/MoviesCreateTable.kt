package com.softwalter.comercio.adapter.treino01.moviesdb

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.model.*
import com.amazonaws.services.s3.model.Region
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class MoviesCreateTable {
}

fun main(){

    /**
     * encapsulando o metodo tableInstace()
     * para usa-lo em demais lugares
     */
    val (logger: Logger, dynamoDB: DynamoDB) = tableInstance()
    val tablename = "Movies"

    try {
        logger.info("Tentando criar a tabela espere por favor ...")
        val table = dynamoDB.createTable(
             tablename,
            mutableListOf(
                KeySchemaElement("year", KeyType.HASH),
                KeySchemaElement("title", KeyType.RANGE)),
            mutableListOf(
                AttributeDefinition("year", ScalarAttributeType.N),
                AttributeDefinition("title", ScalarAttributeType.S)),
            ProvisionedThroughput(10L, 10L))
        table.waitForActive()
        logger.info("SUCESSO!!! Tabela criada.")
    }
    catch (e: Exception){
        logger.info("Erro ao cria a Tabela ...")
        logger.info("Menssagem: ${e.message}")
    }
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

fun tableInstance(): Pair<Logger, DynamoDB> {
    val logger = LoggerFactory.getLogger(MoviesCreateTable::class.java)

    val client: AmazonDynamoDB = AmazonDynamoDBClientBuilder
        .standard()
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                "http://localhost:8000", Region.US_East_2.name
            )
        )
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(
                    "fakeMyAccessKeyId", "fakeSecretAccessKe"
                )
            )
        )
        .build()
    val dynamoDB: DynamoDB = DynamoDB(client)
    return Pair(logger, dynamoDB)
}
