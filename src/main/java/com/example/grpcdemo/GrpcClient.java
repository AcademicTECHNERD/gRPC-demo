package com.example.grpcdemo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        com.example.grpc.HelloServiceGrpc.HelloServiceBlockingStub stub = com.example.grpc.HelloServiceGrpc.newBlockingStub(channel);
        com.example.grpc.HelloResponse response = stub.sayHello(com.example.grpc.HelloRequest.newBuilder().setName("唐华星").build());

        System.out.println("Response from server: " + response.getMessage());

        channel.shutdown();
    }
}
