package diamond;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class DiamondTest {

    @Test
    public void a() {
        diamondShouldBe('A', "A");
    }

    @Test
    public void b() {
        diamondShouldBe('B',
                " A",
                "B B",
                " A"
        );
    }

    @Test
    public void c() {
        diamondShouldBe('C',
                "  A",
                " B B",
                "C   C",
                " B B",
                "  A"
        );
    }

    @Test
    public void d() {
        diamondShouldBe('D', "   A",
                "  B B",
                " C   C",
                "D     D",
                " C   C",
                "  B B",
                "   A"
        );
    }

    @Test
    public void e() {
        diamondShouldBe('e',
                "    a",
                "   b b",
                "  c   c",
                " d     d",
                "e       e",
                " d     d",
                "  c   c",
                "   b b",
                "    a"
        );
    }

    private void diamondShouldBe(char c, String... diamond) {
        Assertions.assertThat(diamond(c)).isEqualTo(Arrays.stream(diamond).reduce((s1, s2) -> s1 + "\n" + s2).orElseThrow(RuntimeException::new));
    }

    private String diamond(char c) {
        boolean lower = Character.isLowerCase(c);
        c = Character.toUpperCase(c);
        int diamondDepth = indexInAlphabet(c);
        String res = "";

        for (char floorChar = 'A'; floorChar < c; floorChar++) {
            res += diamondFloor(floorChar, diamondDepth);
        }

        res += diamondFloor(c, diamondDepth);

        for (char floorChar = (char) (c - 1); floorChar >= 'A'; floorChar--) {
            res += diamondFloor(floorChar, diamondDepth);
        }

        res = res.substring(0, res.lastIndexOf('\n'));
        return lower ? res.toLowerCase() : res;
    }

    private int indexInAlphabet(char floorChar) {
        return floorChar - 'A' + 1;
    }

    private String diamondFloor(char c, int diamondDepth) {
        int floor = indexInAlphabet(c);
        return spaces(diamondDepth - floor) + diamondLine(c) + "\n";
    }

    private String diamondLine(char c) {
        if (c == 'A') {
            return "A";
        }

        int alphabetIndex = indexInAlphabet(c);
        int nbCharInFloor = Math.abs(alphabetIndex * 2 - 1);
        return c + spaces(nbCharInFloor - 2) + c;
    }

    private String spaces(int nb) {
        return new String(new char[nb]).replace("\0", " ");
    }

}
