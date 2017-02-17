package cn.newphy.scloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ComputeService {

    @Autowired
    private RestTemplate restTemplate;
	
    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService() {
    	String result = restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20", String.class).getBody();
    	return result;
    }
    
    public String addServiceFallback() {
        return "error";
    }
}
