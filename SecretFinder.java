import java.io.*;
import java.math.BigInteger;
import java.util.*;
import org.json.JSONObject;

public class SecretFinder {

    public static void main(String[] args) throws Exception {
        JSONObject test1 = new JSONObject(new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("testcase1.json"))));
        JSONObject test2 = new JSONObject(new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("testcase2.json"))));

        BigInteger c1 = findSecret(test1);
        BigInteger c2 = findSecret(test2);

        System.out.println("Secret from Testcase 1: " + c1);
        System.out.println("Secret from Testcase 2: " + c2);
    }

    private static BigInteger findSecret(JSONObject input) {
        int k = input.getJSONObject("keys").getInt("k"); 

        List<BigInteger> xList = new ArrayList<>();
        List<BigInteger> yList = new ArrayList<>();

        for (String key : input.keySet()) {
            if (key.equals("keys")) continue; 
            if (xList.size() >= k) break;     

            int x = Integer.parseInt(key); 
            JSONObject pair = input.getJSONObject(key);
            int base = Integer.parseInt(pair.getString("base"));  
            BigInteger y = new BigInteger(pair.getString("value"), base); 

            xList.add(BigInteger.valueOf(x));
            yList.add(y);
        }
        return lagrangeInterpolationAtZero(xList, yList);
    }

    private static BigInteger lagrangeInterpolationAtZero(List<BigInteger> x, List<BigInteger> y) {
        int k = x.size();
        BigInteger result = BigInteger.ZERO;
        for (int j = 0; j < k; j++) {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;

            for (int m = 0; m < k; m++) {
                if (m == j) continue;

                numerator = numerator.multiply(x.get(m).negate()); 
                denominator = denominator.multiply(x.get(j).subtract(x.get(m))); 
            }

            BigInteger term = y.get(j).multiply(numerator).divide(denominator);
            result = result.add(term);
        }

        return result;
    }
}
