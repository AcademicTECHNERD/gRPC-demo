package com.example.grpcdemo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new HelloServiceImpl())
                .build();

        server.start();
        System.out.println("gRPC Server started on port 9090");
        server.awaitTermination();
    }

    static class HelloServiceImpl extends com.example.grpc.HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void sayHello(com.example.grpc.HelloRequest request, StreamObserver<com.example.grpc.HelloResponse> responseObserver) {
            String name = request.getName();
            String message = "Hello, " + name;

            com.example.grpc.HelloResponse response = com.example.grpc.HelloResponse.newBuilder().setMessage(message).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
