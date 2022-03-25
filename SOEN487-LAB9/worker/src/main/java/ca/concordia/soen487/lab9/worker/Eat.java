package ca.concordia.soen487.lab9.worker;

import org.camunda.bpm.client.ExternalTaskClient;

public class Eat {

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("eat")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    System.out.println("EATING...");

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }

}
