public class Test {
    static int recursive_count = 0;
    static int count = 0;

    // Recursive method to calculate Fibonacci number
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        recursive_count++;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Non-recursive method to calculate Fibonacci number
    public static int fibonacciNonRecursive(int n) {
        if (n <= 1) {
            return n; // Base case
        }
        int a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b; // Fibonacci calculation step
            a = b; // Update a to b
            b = c; // Update b to c
            count++; // Increment non-recursive step count (each operation)
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Recursive Fibonacci of " + n + " is: " + fibonacciRecursive(n));
        System.out.println("Recursive steps: " + recursive_count); // Display recursive steps
        System.out.println("Non-recursive Fibonacci of " + n + " is: " + fibonacciNonRecursive(n));
        System.out.println("Non-Recursive steps: " + count); // Display non-recursive steps
    }
}

