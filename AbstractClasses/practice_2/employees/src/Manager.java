public class Manager implements Employee {

    private double salary = 0;
    private double income = 0;

    public Manager(double salary, double income, Company company) {
        this.salary = salary;
        this.income = income;
        company.setIncome(income);
    }

    @Override
    public double getMonthSalary() {
        return Math.round(100 * (salary + 0.05 * income)) / 100.0;
    }


}
