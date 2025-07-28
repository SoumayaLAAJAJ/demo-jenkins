package com.formation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.formation.data.entity.CustomerEntity;
import com.formation.data.repository.CustomerRepository;
import com.formation.web.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class) // 1 : Ajoute MockitoExtension
public class CustomerServiceTest {

  @InjectMocks // 2 : Injecte les mocks dans le service à tester (CustomerService)
  CustomerService customerService;

  @Mock // 3 : Crée un mock pour le repository
  CustomerRepository customerRepository;

  @Test // 4 : Teste la méthode getAllCustomers
  void getAllCustomers(){

    //Given 5 : Configure le mock pour retourner une liste de 2 clients
    Mockito.doReturn(getMockCustomers(2)).when(customerRepository).findAll();

    //When 6 : Appelle la méthode à tester
    List<Customer> customers = this.customerService.getAllCustomers();

    //Then 7 : Vérifie que la méthode a retourné 2 clients
    assertEquals(2, customers.size());
  }

  // 8 : La méthode qui retourne une liste de clients
  private Iterable<CustomerEntity> getMockCustomers(int size){
    List<CustomerEntity> customers = new ArrayList<>(size);
    for(int i=0;i<size;i++){
      customers.add(new CustomerEntity(UUID.randomUUID(), "firstName" + i, "lastName" + i,
          "email"+i+"@test.com", "555-515-1234", "1234 Main Street"));
    }
    return customers;
  }

}

