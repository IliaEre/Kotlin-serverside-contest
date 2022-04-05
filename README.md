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
> classic-service (Spring web)  
Size plain: 22 201 bytes (25 KB on disk)  
Size jar: 30 642 861 bytes (30,6 MB on disk)  
Docker image with Jib: 300MB  
Docker image with SB: 286MB  

> reactive-service (Spring webflux + native)    
Size aot: 121 474 bytes (135 KB on disk)  
Size plain: 21 926 bytes (25 KB on disk)  
Size jar: 35 031 551 bytes (35,7 MB on disk)  
Docker images with jib: 304MB  
Docker image with SB native: 304MB  

> ktor-service (Ktor)  
Size jar: 69 925 bytes (74 KB on disk)  
docker image with Jib: 243MB  

> ktor-koin-api (Ktor + Koin)  
Size jar: 96 634 bytes (98 KB on disk)  
docker image with Jib: 289MB  

# Enjoy!
