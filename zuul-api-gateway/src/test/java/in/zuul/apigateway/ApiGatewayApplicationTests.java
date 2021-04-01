package in.zuul.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class ApiGatewayApplicationTests {


    private RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:9191/department/test?tenantId=12345";

    @Test
    void contextLoads() {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("::::::Thread " + Thread.currentThread().getName());
                String finalData = restTemplate.getForObject(url, String.class);
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("::::::Thread " + Thread.currentThread().getName());
                String finalData = restTemplate.getForObject(url, String.class);
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                System.out.println("::::::Thread " + Thread.currentThread().getName());
                String finalData = restTemplate.getForObject(url, String.class);
            }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                System.out.println("::::::Thread " + Thread.currentThread().getName());
                String finalData = restTemplate.getForObject(url, String.class);
            }
        };

        Thread t5 = new Thread() {
            @Override
            public void run() {
                System.out.println("::::::Thread " + Thread.currentThread().getName());
                String finalData = restTemplate.getForObject(url, String.class);
            }
        };

        Thread t6 = new Thread() {
            @Override
            public void run() {
                System.out.println("::::::Thread " + Thread.currentThread().getName());
                String finalData = restTemplate.getForObject(url, String.class);
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

}
