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
        else if(!cep.matches("[0-9]{5}-[0-9]{3}".toRegex())) {  // validação de formato
            logger.warn("......Erro de validação, cep invalido.......")

            val erro = Status.INVALID_ARGUMENT.withDescription("Cep com formato invalido")
                .augmentDescription("formato esperado 5555-555")
                .asRuntimeException()
            responseObserver?.onError(erro)
        }
        else {

            logger.info("Calculando frete para request: $request")

            var valor = 0.0
            // validação interna
            try {
                valor = Random.nextDouble(from = 0.0, until = 140.0)
            }
            catch (e: Exception) {
                logger.warn("Erro na logica de calculo do frete")
                responseObserver?.onError(Status.INTERNAL.withDescription(e.message).withCause(e).asRuntimeException())
            }


            val response = CalculaFreteResponse.newBuilder()
                .setCep(request!!.cep)
                .setValor(valor)
                .build()

            logger.info("Frete calculado: $response")
            responseObserver!!.onNext(response)
            // finalizar a requisição
            responseObserver.onCompleted()

        }

    }
}