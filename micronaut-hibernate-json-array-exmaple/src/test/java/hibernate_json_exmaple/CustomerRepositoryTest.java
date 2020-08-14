package hibernate_json_exmaple;

import hibernate_json_exmaple.entities.Customer;
import hibernate_json_exmaple.entities.events.AccountClosedEvent;
import hibernate_json_exmaple.entities.events.AccountOpenedEvent;
import hibernate_json_exmaple.repositories.CustomerRepository;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest(environments = "sqlite")
public class CustomerRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    public void should_save_events_json(){

        Customer customer = new Customer();
        customer.setId(1);

        // set events
        customer.getEvents().add(new AccountOpenedEvent("1"));
        customer.getEvents().add(new AccountClosedEvent("2"));
        customer.getEvents().add(new AccountOpenedEvent("3"));
        customer.getEvents().add(new AccountClosedEvent("4"));

        customerRepository.save(customer);

        Customer saved = customerRepository.findById(1).orElse(null);

    }
}
