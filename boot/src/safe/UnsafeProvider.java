package safe;

import sun.misc.*;

public class UnsafeProvider {
    private static final Unsafe theUnsafe = Unsafe.getUnsafe();

    public static Unsafe getUnsafe() {
        return theUnsafe;
    }
}