package com.softwalter.comercio.adapter.treino2.domaim

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.s3.model.Region
import javax.inject.Singleton

@Singleton
class ConnectionDynamoDB(
) {

    fun tableConnection(): DynamoDBMapper {
        val clientBuilder = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        AwsClientBuilder.EndpointConfiguration(
                                "http://localhost:8002", Region.US_East_2.name
                              //  "dynamodb-local", Region.US_East_2.name
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
        return DynamoDBMapper(clientBuilder)
    }

}
