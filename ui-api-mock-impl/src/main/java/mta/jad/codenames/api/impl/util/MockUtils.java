package mta.jad.codenames.api.impl.util;

import java.util.Random;

public class MockUtils {
    private static final Random random = new Random();

    public static boolean randomBoolean(double chance) {
        return random.nextDouble() < chance;
    }
}
