package br.com.zup

import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import kotlin.random.Random

// anotação serve para que o Micronaut reconheça a classe
@Singleton
class FretesGrpcServer:  FretesServiceGrpc.FretesServiceImplBase(){

    private val logger = LoggerFactory.getLogger(FretesGrpcServer::class.java)

    // sobrescrever o metodo

    override fun calculaFrete(request: CalculaFreteRequest?, responseObserver: StreamObserver<CalculaFreteResponse>?) {
        // logica aqui

        val cep = request!!.cep

        // validação caso o cep esteja nulo ou em branco

        if (cep == null || cep.isBlank()) {

            logger.warn("......Erro de validação, cep nulo ou vazio.......")

            val erro = Status.INVALID_ARGUMENT.withDescription("O cep deve ser informado")
                .asRuntimeException()
            responseObserver?.onError(erro)
        }
        else {

            logger.info("Calculando frete para request: $request")

            val response = CalculaFreteResponse.newBuilder()
                .setCep(request!!.cep)
                .setValor(Random.nextDouble(from = 0.0, until = 140.0))
                .build()

            logger.info("Frete calculado: $response")
            responseObserver!!.onNext(response)
            // finalizar a requisição
            responseObserver.onCompleted()

        }

    }
}