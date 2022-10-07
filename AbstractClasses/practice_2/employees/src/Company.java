import java.util.*;
import java.util.Comparator;
public class Company {
    private  double income = 0;

    private ArrayList<Employee> employees;

    public Company() {
        employees = new ArrayList<>();
    }

    public List<Employee> getTopSalaryStaff(int count){

        Comparator lowesComparator = new LowesComparator();
        Collections.sort(employees, lowesComparator);
        ArrayList<Employee> out = new ArrayList<>();
        if (count <= 0) {
            return out;
        }
        if (count > employees.size()) {
            count = employees.size();
        }
        for (int i=0; i < count; i++){
            out.add(employees.get(i));
        }
        return out;


    }

    public List<Employee> getLowesSalaryStaff(int count){
        Comparator topComparator = new TopComparator();
        Collections.sort(employees, topComparator);
        ArrayList<Employee> out = new ArrayList<>();
        if (count <= 0) {
            return out;
        }
        if (count > employees.size()) {
            count = employees.size();
        }
        for (int i=0; i < count; i++){
            out.add(employees.get(i));
        }
        return out;
    }

    public void hire(Employee employee){
        employees.add(employee);


    }

    public void hireAll(Collection<Employee> employeeList) {
        employees.addAll(employeeList);
    }

    public void fire(Employee employee){
        if (employees.contains(employee)) {
            employees.remove(employee);
        }

    }

    public void setIncome(double income) {
        this.income += income;
    }

    public  double getIncome(){
        return income;

    }


}
