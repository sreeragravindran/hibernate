package hibernate_json_exmaple.repositories;

import hibernate_json_exmaple.entities.Customer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public abstract class CustomerRepository implements CrudRepository<Customer, Integer>{
}
