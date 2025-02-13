import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTestes {
    public static void main(String[] args) {
        System.out.println("Hello World");
        List<Integer>numerosSorteio = new ArrayList<>();
        numerosSorteio.add(1);
        numerosSorteio.add(2);
        numerosSorteio.add(3);
        numerosSorteio.add(4);
        numerosSorteio.add(5);
        numerosSorteio.add(6);
        Collections.shuffle(numerosSorteio);
        System.out.println(numerosSorteio);


    }
}
