package io.apraskal;

/** @author Andrew Praskala (801188019) */

import io.apraskal.cli.CLIRunner;

public class App
{
    public static void main(String[] args) {
        CLIRunner program = CLIRunner.getInstance();
        program.runCLI();
    }
}