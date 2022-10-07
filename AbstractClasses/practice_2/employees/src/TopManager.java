public class TopManager implements Employee{
    private double salary = 0;
    private Company company;

    public TopManager(double salary, Company company) { //income = 0
        this.salary = salary;
        this.company = company;
    }


    @Override
    public double getMonthSalary() {
        if (company.getIncome() > 10000000){
            return Math.round((salary + salary * 1.5) * 100) / 100.0;
        }
        return salary;
    }


}
