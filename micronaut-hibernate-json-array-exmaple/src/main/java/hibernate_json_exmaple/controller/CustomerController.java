package hibernate_json_exmaple.controller;

//import helloworld.entities.Customer;
//import helloworld.repositories.CustomerRepository;
import hibernate_json_exmaple.entities.Address;
import hibernate_json_exmaple.entities.Customer;
import hibernate_json_exmaple.entities.events.AccountClosedEvent;
import hibernate_json_exmaple.entities.events.AccountOpenedEvent;
import hibernate_json_exmaple.repositories.CustomerRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;

import io.reactivex.Single;
import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

import javax.inject.Inject;
import java.util.*;

@Controller("/customer")
public class CustomerController {

    @Inject
    CustomerRepository customerRepository;

    @Get("/save/{name}")
    public String save(String name) throws Exception{

        Customer customer = new Customer();
        customer.setId((int)customerRepository.count() + 1);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", name);
        attributes.put("age", 21);

        customer.setAddress(new Address("line 1", "line 2", "1231"));
        customerRepository.save(customer);

        return "saved successfully";
    }

    @Get("/save/{id}/events")
    public HttpResponse saveWithEvents(Integer id) throws Exception{

        Customer customer = new Customer();
        customer.setId(id);

        // set address

        customer.setAddress(new Address("1/24", "sky villa, CA", "1222" ));

        // set events
        customer.getEvents().add(new AccountOpenedEvent("1"));
        customer.getEvents().add(new AccountClosedEvent("2"));
        customer.getEvents().add(new AccountOpenedEvent("3"));
        customer.getEvents().add(new AccountClosedEvent("4"));

        customerRepository.save(customer);

        return HttpResponse.created(customer);
    }

    @Get("/get/{id}")
    public Customer get(int id) throws Exception{
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer;
    }

    @Get("/get/all")
    public Iterable<Customer> get() throws Exception{
        Iterable customers =  customerRepository.findAll();

        customers.forEach((c)-> {
            System.out.println(c.toString());
        });

        return customers;
    }

}