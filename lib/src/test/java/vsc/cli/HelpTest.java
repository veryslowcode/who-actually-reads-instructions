package vsc.cli;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream streamSpy = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(streamSpy));
    }

    @Test 
    void displayHelpBase() {
        var help = new Help();
        var format = help.format;
        
        help.color = false;
        help.displayHelp();
        assertEquals(streamSpy.toString().trim(), format);
    }

    @Test 
    void displayHelpArguments() {
        var arguments = new HashMap<String, String>(
            Map.of("test", "Description")
        );
        var help = new Help(arguments, new HashMap<String, String>());
        
        help.color = false;
        help.displayHelp();
        var actual = streamSpy.toString().trim();
        assertTrue(actual.contains("test"));
        assertTrue(actual.contains("Description"));
    }
    
    @Test 
    void displayHelpFlags() {
        var flags = new HashMap<String, String>(
            Map.of("t", "Description")
        );
        var help = new Help(new HashMap<String, String>(), flags);
        
        help.color = false;
        help.displayHelp();
        var actual = streamSpy.toString().trim();
        assertTrue(actual.contains("t"));
        assertTrue(actual.contains("Description"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
}
