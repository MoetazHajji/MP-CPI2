package Interface;

import entities.Patient;

import java.util.List;
import java.util.Scanner;

interface IPatientService {

    void add(Patient patient);

    void delete(Scanner scanner);

    List<Patient> getAll();

    void getById(Scanner scanner);

    void update(Scanner scanner);
}
