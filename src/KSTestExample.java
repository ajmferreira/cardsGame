import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;

public class KSTestExample {

    double[] dataA;
    double[] dataB;

    public KSTestExample(double[] dataA, double[] dataB) {
        this.dataA = dataA;
        this.dataB = dataB;
    }

    public double KSTest() {
        // Create a Kolmogorov-Smirnov test instance
        KolmogorovSmirnovTest ksTest = new KolmogorovSmirnovTest();
        double criticalValue = 0;
        // Perform the KS test
        try {
            double ksStatistic = ksTest.kolmogorovSmirnovTest(dataA, dataB);

            // Define a significance level (alpha)
            double alpha = 0.05;

            // Calculate the critical value based on alpha and sample sizes
            int n1 = dataA.length;
            int n2 = dataB.length;
            criticalValue = 1.36 / Math.sqrt(n1 + n2);
            System.out.printf("Critical value: %4.4f\n", criticalValue);
            System.out.printf("ksStatistic value: %4.4f\n", ksStatistic);

            // Compare the KS statistic to the critical value
            if (ksStatistic > criticalValue) {
                System.out.println("Reject the null hypothesis: Distributions are different.");
            } else {
                System.out.println("Fail to reject the null hypothesis: Distributions are similar.");
            }
        } catch (MathIllegalArgumentException | MathInternalError e) {
            e.printStackTrace();
        }
        return criticalValue;
    }

}
