package com.softwalter.comercio.adapter.comerciodb.config.comertciodb

import org.slf4j.Logger

class InsertData {

}
fun main(){
    val (tableName: String, partitionKeyName, sortKeyName) = chavesDB()
    val (logger: Logger, client) = dynamoDBConnection()
}