
--- 

Read more useful here:
> https://litote.org/kmongo/quick-start/  
> https://github.com/Kotlin/kotlinx.serialization  
> https://litote.org/kmongo/extensions-overview/#insert
> https://ktor.io/docs/requests.html#query_parameters
> https://ktor.io/docs/creating-http-apis.html#defining-the-routing-for-customers
> https://docs.mongodb.com/manual/reference/connection-string/
> http://litote.org/kmongo/dokka/kmongo/org.litote.kmongo/index.html
> https://kotlinlang.org/docs/gradle.html#set-custom-jdk-home
> https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.serialization
> https://ktor.io/docs/micrometer-metrics.html#prometheus_endpoint  

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