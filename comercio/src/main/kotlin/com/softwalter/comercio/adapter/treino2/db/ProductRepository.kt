package com.softwalter.comercio.adapter.treino2.db

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.softwalter.comercio.adapter.treino2.domaim.ConnectionDynamoDB
import com.softwalter.comercio.adapter.treino2.domaim.Product
import com.sofwalter.adapter.db.ProductEntidade
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ProductRepository(

        var dynamoDBMapper: ConnectionDynamoDB
) {
    fun salveProduct(productEntidade: ProductEntidade): Product {
        val logger = LoggerFactory.getLogger(this::class.java)
        try {
            logger.info("Iniciando a conexao")
            dynamoDBMapper.tableConnection().save(productEntidade)
            logger.info("dymanoDBMapper $dynamoDBMapper")
            return productEntidade.toProduct()
        }
        catch (e: Exception){
        logger.info("Erro ao inserirdado na Tabela ...")
        logger.info("Menssagem: ${e.message}")
            throw Exception()
    }
        catch (ase: AmazonServiceException, ){
            logger.error("Operacao nao completada")
            logger.error("Menssagem: ${ase.message}")
            logger.error("Mensagem de erro: ${ase.statusCode}")
            logger.error("Status HTTP: ${ase.errorCode}")
            logger.error("Erro codigo AWS: ${ase.errorType}")
            logger.error("ID da requiscao: ${ase.errorMessage}")
            throw Exception()
        }
        catch (ace: AmazonClientException){
            logger.error("Erro interno ocorrido na comunicação do DynamoDB")
            logger.error("Mensagem de erro ${ace.message}")
            logger.error("Mensagem de erro local ${ace.localizedMessage.toString()}")
            logger.error("Mensagem de causa de erro ${ace.cause}")
            logger.error("Mensagem de erro stack trace ${ace.stackTrace}")
            throw Exception()
        }

    }

}