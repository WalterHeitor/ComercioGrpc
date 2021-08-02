package com.softwalter.comercio.adapter.comerciodb.config.db

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.s3.model.Region

class ConnectionDynamoDB {

    companion object{
        val clientBuilder: AmazonDynamoDB = tableConnection()
        var mapper: DynamoDBMapper = DynamoDBMapper(clientBuilder)
    }
}

fun tableConnection(): AmazonDynamoDB {
    return AmazonDynamoDBClientBuilder
        .standard()
        //.withRegion(AWS_REGION)
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                "http://localhost:8000", Region.US_East_2.name
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
}