import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
        sortBySalaryAndAlphabet(staff);
        System.out.println("Отсортированный список");
        System.out.println(staff);
        for (Employee e : staff) {
            System.out.println(e.getSalary() + " " + e.getName());
        }
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.

        staff.sort((o1, o2) -> {
            int salarySort = o1.getSalary() - o2.getSalary();
            if (salarySort !=0) {
                return salarySort;
            }
            int nameSort = o1.getName().compareTo(o2.getName());
            return nameSort;
        } );
    }
}