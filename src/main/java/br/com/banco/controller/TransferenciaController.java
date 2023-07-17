package br.com.banco.controller;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.web.client.HttpServerErrorException;


@RestController
@RequestMapping("/")
public class TransferenciaController {

  @Autowired
  private ContaRepository contaRepository;
  
  @Autowired
  private TransferenciaRepository transferenciaRepository;

  // retorna todas as optionalTransferencia
  @GetMapping("/transferencias")
  public List<Transferencia> getAllTransferencias() {
    return transferenciaRepository.findAll();
  }

  // retorna a transferencia por ID.
  @GetMapping("/transferencias/conta/{id}")
  public List<Transferencia> getTransferenciaById(@PathVariable long id ) {
    List<Transferencia> list = transferenciaRepository.findAll().stream().filter(transferencia -> transferencia.getContaId() == id).collect(Collectors.toList());
    if(!list.isEmpty()) {
      return (list);
    } else {
      throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/transferencias/nome/{nome}")
  public List<Transferencia> getTransferenciaByName(@PathVariable String nome ) {
    List<Transferencia> list = transferenciaRepository.findAll().stream()
            .filter(transferencia -> transferencia.getNomeOperador() != null)
            .filter(transferencia -> transferencia.getNomeOperador().equals(nome)).collect(Collectors.toList());
    if(!list.isEmpty()) {
      return (list);
    } else {
      throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/transferencias/periodo/{inicio}/{fim}")
  public List<Transferencia> getTransferenciaByPeriod(@PathVariable String inicio, @PathVariable String fim ) {
    OffsetDateTime dataInicio = OffsetDateTime.parse(inicio, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx"));
    OffsetDateTime dataFim = OffsetDateTime.parse(fim, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx"));

    List<Transferencia> list = transferenciaRepository.findAll().stream()
            .filter(transferencia -> transferencia.getData().isAfter(dataInicio))
            .filter(transferencia -> transferencia.getData().isBefore(dataFim))
            .collect(Collectors.toList());
    if (!list.isEmpty()) {
      return (list);
    } else {
      throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/transferencias/filtro/{id}/{nome}/{inicio}/{fim}")
  public List<Transferencia> getTranferenciaByFilter(@PathVariable long id, @PathVariable String nome, @PathVariable String inicio, @PathVariable String fim) {
    OffsetDateTime dataInicio = OffsetDateTime.parse(inicio, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx"));
    OffsetDateTime dataFim = OffsetDateTime.parse(fim, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx"));

    List<Transferencia> list = transferenciaRepository.findAll().stream()
            .filter(transferencia -> transferencia.getContaId() == id)
            .filter(transferencia -> transferencia.getNomeOperador() != null)
            .filter(transferencia -> transferencia.getNomeOperador().equals(nome))
            .filter(transferencia -> transferencia.getData().isAfter(dataInicio))
            .filter(transferencia -> transferencia.getData().isBefore(dataFim))
            .collect(Collectors.toList());

    if (!list.isEmpty()) {
      return (list);
    } else {
      throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }
  }

  // update employee rest api
  // @PutMapping("/employees/{id}")
  // @ResponseBody
  // public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
  //   Optional<Employee> optionalEmployee = employeeRepository.findById(id);
  //   if (!optionalEmployee.isPresent()) {
  //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //   }

  //   Employee employee = optionalEmployee.get();
  //   employee.setFirstName(employeeDetails.getFirstName());
  //   employee.setLastName(employeeDetails.getLastName());

  //   Employee updatedEmployee = employeeRepository.save(employee);
  //   return ResponseEntity.ok(updatedEmployee);
  // }

  // delete employee rest api
  // @DeleteMapping("/employees/{id}")
  // @ResponseBody
  // public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
  //   Optional<Employee> optionalEmployee = employeeRepository.findById(id);
  //   if (!optionalEmployee.isPresent()) {
  //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  //   }

  //   Employee employee = optionalEmployee.get();
  //   employeeRepository.delete(employee);
  //   Map<String, Boolean> response = new HashMap<>();
  //   response.put("deleted", Boolean.TRUE);
  //   return ResponseEntity.ok(response);
  // }
}