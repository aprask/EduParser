package io.apraskal;

import io.apraskal.cli.CLIRunner;

public class App
{
    public static void main(String[] args) {
        CLIRunner program = CLIRunner.getInstance();
        program.runCLI();
    }
}