package safe;

import sun.misc.*;

import java.lang.reflect.*;

public class UnsafeProvider {
    private static final Unsafe unsafe;

    static {
        Unsafe temp = null;
        try {
            temp = Unsafe.getUnsafe(); // first try if the class is in the boot class path
            System.out.println("Unsafe from Unsafe.getUnsafe()");
        } catch (SecurityException ex) {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                temp = (Unsafe) f.get(null);
                System.out.println("Unsafe via reflection");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        unsafe = temp;
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}