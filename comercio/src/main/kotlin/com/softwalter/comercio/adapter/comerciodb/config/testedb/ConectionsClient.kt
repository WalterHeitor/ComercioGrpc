package com.softwalter.comercio.adapter.comerciodb.config.testedb


import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.TableCollection
import com.amazonaws.services.dynamodbv2.model.*
import com.amazonaws.services.s3.model.Region.US_East_2
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateTable {

}

fun main() {

    val (tableName: String, partitionKeyName, sortKeyName) = chavesDB()
    val (logger: Logger, client) = dynamoDBConnection()
    try {

        logger.info("tentando cria tabela")
        val request = CreateTableRequest()
            .withTableName(tableName)
            .withAttributeDefinitions(AttributeDefinition(partitionKeyName, ScalarAttributeType.S))
            .withAttributeDefinitions(AttributeDefinition(sortKeyName, ScalarAttributeType.S))
            .withKeySchema(
                mutableListOf(
                    KeySchemaElement(partitionKeyName, KeyType.HASH),
                    KeySchemaElement(sortKeyName, KeyType.RANGE)
                )
            )
            .withBillingMode(BillingMode.PAY_PER_REQUEST)
        client.createTable(request).waitForActive()
        logger.info("Tabela: $tableName criada com sucesso!!!")
    }
    catch (e: Exception) {
        logger.error("Erro a criar tabela ...")
        logger.error(" Menssagem: ${e.message}")
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

fun chavesDB(): Triple<String, String, String> {
    val tableName: String = "dynamodb_table"
    val partitionKeyName = "PK"
    val sortKeyName = "SK"
    return Triple(tableName, partitionKeyName, sortKeyName)
}

fun dynamoDBConnection(): Pair<Logger, DynamoDB> {
    val awsRegion: String = US_East_2.name
    val logger: Logger = LoggerFactory.getLogger(CreateTable::class.java)
    val clientBuilder: AmazonDynamoDB = amazonDynamoDB(awsRegion)
    val client = DynamoDB(clientBuilder)
    val results: TableCollection<ListTablesResult> = client.listTables()
    logger.info("Listagem de Tabelas existentes ... ${results.forEach { it.tableName.toString() }}")
    return Pair(logger, client)
}

fun amazonDynamoDB(awsRegion: String): AmazonDynamoDB {
    val clientBuilder: AmazonDynamoDB = AmazonDynamoDBClientBuilder
        .standard()
        //.withRegion(AWS_REGION)
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                "http://localhost:8000", awsRegion
            )
        )
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(
                    "fakeMyAccessKeyI", "fakeSecretAccessKe"
                )
            )
        )
        .build()
    return clientBuilder
}