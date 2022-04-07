# Why Kotlin and Ktor?

### version 0.0.3-SNAPSHOT

Technologies which like to use:  
Languages: `Kotlin`  
Frameworks: `Spring boot, Spring boot webflux, Ktor, Koin`  
Libraries: `Kmongo, Reactive Mongo, Spring data` 
DataBase: `MongoDB`  
Build: `Gradle kts`  
Deploy: `Docker-desktop, minikube, AWS`  

--- 

### services:
> `classic-service (Spring web)`  
> `reactive-service (Spring webflux + native)`     
> `ktor-service (Ktor)`    
> `ktor-koin-api (Ktor + Koin)`  

| service             | jar size | plain jar size  | aot jar size  | Jib image | SB image | Startup time |   
| ------------------- | -------- | --------------- | ------------- | --------- | -------- | ------------ |
| `classic-service`   | 30,6 MB  | 25 KB           | X             | 300MB     | 286MB    |              |
| `reactive-service`  | 35,7 MB  | 25 KB           | 135 KB        | 304MB     | 304MB    |              |
| `ktor-service`      | 74 KB    | X               | X             | 243MB     | X        |              |
| `ktor-koin-service` | 98 KB    | X               | X             | 289MB     | X        |              |

# Enjoy!
