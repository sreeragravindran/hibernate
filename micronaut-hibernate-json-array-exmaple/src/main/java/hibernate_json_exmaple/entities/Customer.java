package hibernate_json_exmaple.entities;

import hibernate_json_exmaple.common.hibernate.AddressConverter;
import hibernate_json_exmaple.common.hibernate.EventListConverter;
import hibernate_json_exmaple.entities.events.Event;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Setter
@Getter
public class Customer {

    public Customer(){
        this.events = new ArrayList<>();
    }

    @Id
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    @Convert(converter = AddressConverter.class)
    private Address address;

    @Column(name = "events")
    @Convert(converter = EventListConverter.class)
    private List<Event> events;

}
