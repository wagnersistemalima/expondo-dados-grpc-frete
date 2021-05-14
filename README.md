# GRPC -> Chamada de Procedimento Remoto
* Framework da google

# Caracteristicas:
* Simple & idiomatic
* Performant & scalable
* Interoperable & extensible
* Funciona em cima do http2 -> https
* Util para microsserviços
* Utiliza protobuf, formato binario para trafegar na rede

### Protobuf
* Uma alternativa para a serialização em JSON e XML
* Ele faz de forma binaria
* Desta forma, os dados ficam bem menores e compactos, e podem ser trafegados muito mais rapidamente, e com menor
custo na rede, persistidos em discos


# expondo-dados-grpc-frete
* Criação do server frete porta 50051
* Implementação do endpoint grpc
* implementação do calculo do frete
* expondo dados via grpc.

### Utilizei a ferramenta BloomRpc para consumir o end point

* Application Type: gRPC Application
* Micronaut Version: 2.5.3
* Java Version: 11
* Language: Kotlin
* Build: Gradlle Kotlin
* Test Framework: Junit


