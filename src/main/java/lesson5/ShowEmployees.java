package lesson5;

public class ShowEmployees {
    public static Employee[] employees = new Employee[5];

    public static void fillEmployees() {
        employees[0] = new Employee("Alex", 41, "tester", "alex@tc.com", "896849000", 35000);
        employees[1] = new Employee("Vic", 38, "programmer", "vic@tc.com", "896849001", 35000);
        employees[2] = new Employee("Max", 44, "manager", "max@tc.com", "896849002", 55000);
        employees[3] = new Employee("Boris", 36, "devops", "boris@tc.com", "896849003", 35000);
        employees[4] = new Employee("Stan", 42, "sales", "stan@tc.com", "896849004", 65000);
    }

    public static void showEmployeesOver40() {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i].getEmployeeOver40() != null) {
                employees[i].showEmployee();
            }
        }
    }

    public static void main(String[] args) {
        fillEmployees();
        System.out.println("Employees over 40 yo:\n");
        showEmployeesOver40();
    }

}
