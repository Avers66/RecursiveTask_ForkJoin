public class Arithmetic {
    private int a;
    private int b;

    public Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public int sum(int a, int b) {
        return a + b;
    }
    public int mul(int a, int b) {
        return a * b;
    }
    public int max(int a, int b) {
        return a >= b ? a : b;
    }
    public int min(int a, int b) {
        return a <= b ? a : b;
    }
}
