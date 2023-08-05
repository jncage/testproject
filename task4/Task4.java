import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Task4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Task4 <numbers>");
            return;
        }
        String numbers;
        try {
            numbers = new String(Files.readAllBytes(Path.of(args[0])));
            int[] data = Stream.of(numbers.split(System.lineSeparator())).mapToInt(Integer::parseInt).toArray();
            System.out.println(countMoves(data));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static int countMoves(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int answer = 0;
        for (int num : nums) {
            answer += Math.abs(num - median);
        }
        return answer;
    }
}
