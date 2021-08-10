package com.softwalter.comercio.adapter.treino01.testedb

import com.amazonaws.services.dynamodbv2.document.Item
import org.slf4j.Logger

class InsertData {

}
fun main(){
    val (tableName: String, partitionKeyName, sortKeyName) = chavesDB()
    val (logger: Logger, client) = dynamoDBConnection()
    val item = Item()
        .withPrimaryKey(partitionKeyName, "algum valor")
        .withString(sortKeyName, "outro valor")
        .withString("valor de atributo", "valor do atributo")
        .withNumber("atributo Numero", 123)
    client.getTable(tableName).putItem(item)
}