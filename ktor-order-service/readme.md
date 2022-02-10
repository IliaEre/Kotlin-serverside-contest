# Ktor order server 

## Ktor + Kmongo

--- 

Read more useful here:
> 1. [Kmongo documents](https://litote.org/kmongo/quick-start/)  
> 2. [kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization)  
> 3. [Kmongo insert](https://litote.org/kmongo/extensions-overview/#insert)
> 4. [Ktor param](https://ktor.io/docs/requests.html#query_parameters)  
> 5. [Ktor routing](https://ktor.io/docs/creating-http-apishtml#defining-the-routing-for-customers)  
> 6. [Mongo connection string](https://docs.mongodb.com/manual/reference/connection-string/)  
> 7. [Kmongo](http://litote.org/kmongo/dokka/kmongo/org.litote.kmongo/index.html)
> 8. [JDK setup](https://kotlinlang.org/docs/gradle.html#set-custom-jdk-home)
> 9. [serialization plugin](https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.serialization)
> 10. [Metrics with micrometr](https://ktor.io/docs/micrometer-metrics.html#prometheus_endpoint)  

---

request:
```http request
curl --location --request POST 'http://localhost:8080/v1/order' \
--header 'Content-Type: application/json' \
--header 'Cookie: mongo-express=s%3AnRy5KVqRgZMRf2YvVBmkhj2rqvYXY6o-.aG5jaIAMNvt19JY9L4PrfXMFHci5M%2BfttPI60DrP5Yk' \
--data-raw '{
    "uuid": "some_uuid_here",
    "user" : {
        "uuid": "user_uuid_here",
        "name": "Igor",
        "email": "some_email_here@mail.mail"
    },
    "equity": {
        "name": "APPL",
        "orderName": "order",
        "total": 13000
    },
    "side": "SELL"
}'
```