package com.softwalter.comercio.adapter.comerciodb.percistence

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.softwalter.comercio.adapter.comerciodb.config.testedb.amazonDynamoDB
import com.softwalter.comercio.adapter.comerciodb.entity.ProductEntity
import com.softwalter.comercio.adapter.model.Product
import org.slf4j.LoggerFactory

class ProductRepository(
    private var dynamoDBMapper: DynamoDBMapper,
    var client: AmazonDynamoDB,
    val productEntity: ProductEntity,
) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun create(product: Product) {
        client = amazonDynamoDB(Regions.US_EAST_2.name)
        dynamoDBMapper = DynamoDBMapper(client)
        try {
            logger.info("")
            dynamoDBMapper.save(productEntity.modelToEntity(product))
        } catch (e: Exception) {
            logger.info("Erro ao inserir dado na Tabela ...")
            logger.info("Menssagem: ${e.message}")
        } catch (ase: AmazonServiceException) {
            logger.error("Operacao nao completada")
            logger.error("Menssagem: ${ase.message}")
            logger.error("Mensagem de erro: ${ase.statusCode}")
            logger.error("Status HTTP: ${ase.errorCode}")
            logger.error("Erro codigo AWS: ${ase.errorType}")
            logger.error("ID da requiscao: ${ase.errorMessage}")
        } catch (ace: AmazonClientException) {
            logger.error("Erro interno ocorrido na comunicação do DynamoDB")
            logger.error("Mensagem de erro ${ace.message}")
            logger.error("Mensagem de erro local ${ace.localizedMessage.toString()}")
            logger.error("Mensagem de causa de erro ${ace.cause}")
            logger.error("Mensagem de erro stack trace ${ace.stackTrace}")
        }

    }

}