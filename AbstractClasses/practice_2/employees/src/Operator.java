public class Operator implements Employee{

    private double salary = 0;

    public Operator(double salary) {
        this.salary = salary;
    }

    @Override
    public double getMonthSalary() {
        return Math.round(salary *100) / 100.0;
    }


}
