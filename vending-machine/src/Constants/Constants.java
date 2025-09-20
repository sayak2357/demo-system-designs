package Constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {
    public static final Set<Double> VALID_DENOMINATIONS =
            new HashSet<>(Arrays.asList(0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 100.0, 500.0, 1000.0));
}
