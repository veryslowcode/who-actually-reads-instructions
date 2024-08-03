package vsc.cli;

import java.util.Map;
import java.util.HashMap;

public class Help {
    // Argument, Description
    public Map<String, String> arguments;

    // Flag, Description
    public Map<String, String> flags;
    
    // Indicates whether or not Ansi escape sequences
    // should be used to color output
    public boolean color = true;

    // Command execution format
    public String format = "java -jar <JAR> [ARGUMENTS] [FLAGS]";

    public Help() {
        this.arguments = new HashMap<String, String>();
        this.flags = new HashMap<String, String>();
    }

    public Help(Map<String, String> arguments, Map<String, String> flags) {
        this.arguments = arguments;
        this.flags = flags;
    }

    public void displayHelp() {
        var reset = color ? "\033[0m" : "";
        var prefixGreen = color ? "\033[32m" : "";
        var prefixYellow = color ? "\033[33m" : "";

        System.out.println(prefixGreen + format + reset);

        if (!this.arguments.isEmpty()) {
            System.out.println("Arguments --[ARGUMENT]=[VALUE]: ");
            this.arguments.forEach((argument, description) -> {
                System.out.println("\t" + prefixYellow + argument + reset + ":\t" + description);
            });
        }

        if (!this.flags.isEmpty()) {
            System.out.println("Flags -[FLAG]: ");
            this.flags.forEach((flag, description) -> {
                System.out.println("\t" + prefixYellow + flag + reset + ":\t" + description);
            });
        }
    }
}
