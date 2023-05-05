import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testGame() {
        // Test losing scenario
        String input = "rock\nquit\n";
        provideInput(input);
        Main.main(new String[0]);
        String output = getOutput();
        Assertions.assertTrue(output.contains("Opponent move: paper\nYou lost!\nYou have won 0 games and have lost 1 games."));

        // Test tie scenario
        input = "paper\nquit\n";
        provideInput(input);
        Main.main(new String[0]);
        output = getOutput();
        Assertions.assertTrue(output.contains("Opponent move: scissors\nIt's a tie!\nYou have won 0 games and have lost 1 games."));

        // Test winning scenario
        input = "scissors\nquit\n";
        provideInput(input);
        Main.main(new String[0]);
        output = getOutput();
        Assertions.assertTrue(output.contains("Opponent move: Rock\nYou won!\nYou have won 1 games and have lost 1 games."));

        // Test quitting scenario
        input = "quit\n";
        provideInput(input);
        Main.main(new String[0]);
        output = getOutput();
        Assertions.assertTrue(output.contains("Thanks for playing Rock, Paper, Scissors!"));
    }
}
