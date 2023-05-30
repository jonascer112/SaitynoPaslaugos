package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;
import javax.persistence.OneToMany;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;


@XmlRootElement
@XmlType(propOrder = {"id", "date", "customers", "computers"})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "computer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String date;

    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    @OneToMany(targetEntity = Customer.class, cascade = CascadeType.ALL)
    private List<Customer> customers;

    @XmlElementWrapper(name = "computers")
    @XmlElement(name = "computer")
    @OneToMany(targetEntity = Computer.class, cascade = CascadeType.ALL)
    private List<Computer> computers;

    public Order(){}

    public Order(int id, String date, List<Customer> customers, List<Computer> computers) {
        this.id = id;
        this.date = date;
        this.customers = customers;
        this.computers = computers;
    }

    public Order(String date, List<Customer> customers, List<Computer> computers) {
        this.date = date;
        this.customers = customers;
        this.computers = computers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Computer> getComputer() { return computers; }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toString() {
        return String.format("Order:\n\t Date = %s\n\t" +
                        "Customers: \n\t%s" +
                        "Computers:\n\t\t%s",
                this.date,
                this.customers,
                this.computers,
                constructCustomerString(),
                constructComputerString());
    }

    private String constructCustomerString(){
        String resultCustomer = "";
        for(Customer customer : this.customers){
            resultCustomer += customer.toString();
        }
        return resultCustomer;
    }

    private String constructComputerString(){
        String resultComputer = "";
        for(Computer computer : this.computers) resultComputer += computer.toString();
        return resultComputer;
    }
}
