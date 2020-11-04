import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Block {
    public static void main(String[] args) {

        Nancy nancy = null;
        int a = 1;
        int b = 2;
        int c = a + b;

        System.out.println(c);

    }
}
