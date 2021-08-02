package com.softwalter.comercio.adapter.comerciodb.config.operacoescrud

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.softwalter.comercio.adapter.comerciodb.config.testedb.amazonDynamoDB
import com.softwalter.comercio.adapter.comerciodb.config.testedb.chavesDB
import org.slf4j.LoggerFactory

class DynamoDBMapperCRUDExample {

}
fun main(){
    val (tableName: String, partitionKeyName, sortKeyName) = chavesDB()
  //  val (logger: Logger, client) = dynamoDBConnection()
    testeOperacoesCRUD()
}

@DynamoDBTable(tableName = "catalog_table")
class CatalogItem(
    @DynamoDBHashKey(attributeName = "PK")
    val productId: Int,
    @DynamoDBRangeKey(attributeName = "SK")
    val title: String,
    @DynamoDBAttribute(attributeName = "ISBN")
    val ISBN: String,
    @DynamoDBAttribute(attributeName = "Authors")
    val bookAutors: Set<String>,
){


}
fun testeOperacoesCRUD() {
    val logger = LoggerFactory.getLogger(CatalogItem::class.java)
    val clientBuilder: AmazonDynamoDB = amazonDynamoDB(Regions.US_EAST_2.name)
    val catalogItem = CatalogItem(
        productId = 1,
        title = "Programando em Kotlin",
        ISBN = "611-11111111111",
        bookAutors = hashSetOf("autor1", "autor2", "autor3")
    )
    var mapper: DynamoDBMapper = DynamoDBMapper(clientBuilder)
    try {
        logger.info("Tentando inserir dado na tabela")
        mapper.save(catalogItem)
        logger.info("Dados inseridos na tabela com sucesso!!!")
    }
    catch (e: Exception) {
        logger.error("Erro inserir dado na tabela ...")
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