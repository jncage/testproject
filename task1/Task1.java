public class Task1 {

    private static String circularArrayPath(int n, int m) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < lcm(n, m - 1); i += (m - 1)) {
            path.append((i % n) + 1);
        }

        return path.toString();
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Task1 <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        if (n < 1) {
            System.out.println("<n> should be greater than 0");
            return;
        }
        int m = Integer.parseInt(args[1]);
        if (m < 1) {
            System.out.println("<m> should be greater than 0");
            return;
        }

        String path = circularArrayPath(n, m);
        System.out.println(path);
    }
}
