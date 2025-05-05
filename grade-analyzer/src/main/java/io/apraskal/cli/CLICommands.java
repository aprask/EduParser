package io.apraskal.cli;

import io.apraskal.model.*;

interface CLICommands {
    public Origin processPath(String path);
    public boolean validateFilePath(String path);
    public Origin runProgram();
}
