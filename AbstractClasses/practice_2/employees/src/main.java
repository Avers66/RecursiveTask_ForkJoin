public class main {
    public static void main(String[] args) {
        Company company1 = new Company();

        // Прием на работу
        for (int i = 1; i <= 80; i++) {
            double salary =  50000 + (70000 - 50000) * Math.random();
            double income =  115000 + (140000 - 115000) * Math.random();
            Manager manager = new Manager(salary, income, company1);
            company1.hire(manager);
        }
        for (int i = 1; i <= 180; i++) {
            double salary =  40000 + (60000 - 40000) * Math.random();
            Operator operator = new Operator(salary);
            company1.hire(operator);
        }
        for (int i = 1; i <= 10; i++) {
            double salary = 70000 + (90000 - 70000) * Math.random();
            TopManager topManager = new TopManager(salary, company1);
            company1.hire(topManager);
        }
        // Печать 1
        System.out.println("Наибольшие зарплаты:");
        for (Employee e : company1.getTopSalaryStaff(11)){
            System.out.println(e.getClass().getName() + " " + e.getMonthSalary());
        }
        System.out.println("Наименьшие зарплаты:");
        for (Employee e : company1.getLowesSalaryStaff(30)){
            System.out.println(e.getClass().getName() + " " + e.getMonthSalary());
        }
        // Увольнение 50 %
        System.out.println("Увольнение 50 %");
        int countM = 40;
        int countO = 90;
        int countT =5;
        for (Employee e : company1.getTopSalaryStaff(260)){
            if (e.getClass().getName() == "Manager" && countM > 0) {
                company1.fire(e);
                countM--;
            }
            if (e.getClass().getName() == "Operator" && countO > 0) {
                company1.fire(e);
                countO--;
            }
            if (e.getClass().getName() == "TopManager" && countT > 0) {
                company1.fire(e);
                countT--;
            }
        }
        // Печать 2
        System.out.println("Наибольшие зарплаты:");
        for (Employee e : company1.getTopSalaryStaff(11)){
            System.out.println(e.getClass().getName() + " " + e.getMonthSalary());
        }
        System.out.println("Наименьшие зарплаты:");
        for (Employee e : company1.getLowesSalaryStaff(30)) {
            System.out.println(e.getClass().getName() + " " + e.getMonthSalary());
        }
    }


}



