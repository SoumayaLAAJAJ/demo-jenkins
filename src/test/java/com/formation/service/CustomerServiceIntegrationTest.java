package com.formation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.formation.web.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest // 1: On charge le contexte Spring
@ActiveProfiles("test")  // 2: On charge le profile test
@AutoConfigureTestDatabase(replace= Replace.ANY) // 3: On remplace la base de données par une base de données de test
public class CustomerServiceIntegrationTest {

  @Autowired
  CustomerService service;

  @Test
  void getAllCustomers(){
    
    // 4: On appelle le service pour récupérer la liste des clients
    List<Customer> customers = this.service.getAllCustomers();
    
    // 5: On vérifie que le service retourne bien 5 clients
    assertEquals(5, customers.size());
  }
}

