package io.apraskal;

import io.apraskal.service.*;
import io.apraskal.service.data.*;
import io.apraskal.model.*;
import io.apraskal.cache.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    private static List<String> destTokens1 = new ArrayList<String>();
    private static List<String> destTokens2 = new ArrayList<String>();
    private static List<String> originTokens1 = new ArrayList<String>();
    private static List<String> originTokens2 = new ArrayList<String>();

    private static void testOrigins() {
        originTokens1.add("home");
        originTokens1.add("connjit");
        originTokens1.add("n");
        originTokens1.add("y");
        originTokens1.add("b");
        originTokens1.add("g");
        originTokens1.add("grades.csv");

        originTokens2.add("home");
        originTokens2.add("connjit");
        originTokens2.add("n");
        originTokens2.add("y");
        originTokens2.add("b");
        originTokens2.add("g");
        originTokens2.add("moregrades.csv");
        
        destTokens1.add("home");
        destTokens1.add("connjit");
        destTokens1.add("n");
        destTokens1.add("y");
        destTokens1.add("b");
        destTokens1.add("g");
        destTokens1.add("res1.pdf");

        destTokens2.add("home");
        destTokens2.add("connjit");
        destTokens2.add("n");
        destTokens2.add("y");
        destTokens2.add("b");
        destTokens2.add("g");
        destTokens2.add("res2.pdf");
    }

    private static Origin[] createOrigPaths(String os) {
        String initArg = "";

        if (os.contains("win")) {
            initArg = "C:";
        } else {
            initArg = "/";
        }

        String[] origin1KArgs = new String[originTokens1.size()];
        for (int i = 0; i < originTokens1.size(); i++) {
            origin1KArgs[i] = originTokens1.get(i);
        }
        String[] origin2KArgs = new String[originTokens2.size()];
        for (int i = 0; i < originTokens2.size(); i++) {
            origin2KArgs[i] = originTokens2.get(i);
        }

        Origin fromOrigin1 = new Origin.OriginBuilder()
            .setOs(os)
            .setInitArg(initArg)
            .setKArgs(origin1KArgs)
            .build();
        
        Origin fromOrigin2 = new Origin.OriginBuilder()
            .setOs(os)
            .setInitArg(initArg)
            .setKArgs(origin2KArgs)
            .build();

        Origin[] origins = new Origin[2];
        origins[0] = fromOrigin1;
        origins[1] = fromOrigin2;
        return origins;
    }


    private static Origin[] createDestPaths(String os) {
        String initArg = "";

        if (os.contains("win")) {
            initArg = "C:";
        } else {
            initArg = "/";
        }

        String[] dest1KArgs = new String[destTokens1.size()];
        for (int i = 0; i < destTokens1.size(); i++) {
            dest1KArgs[i] = destTokens1.get(i);
        }
        String[] dest2KArgs = new String[destTokens2.size()];
        for (int i = 0; i < destTokens2.size(); i++) {
            dest2KArgs[i] = destTokens2.get(i);
        }

        Origin toOrigin1 = new Origin.OriginBuilder()
            .setOs(os)
            .setInitArg(initArg)
            .setKArgs(dest1KArgs)
            .build();

        Origin toOrigin2 = new Origin.OriginBuilder()
            .setOs(os)
            .setInitArg(initArg)
            .setKArgs(dest2KArgs)
            .build();

        Origin[] origins = new Origin[2];
        origins[0] = toOrigin1;
        origins[1] = toOrigin2;
        return origins;
    }

    public static void main(String[] args)
    {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("y")) {
                System.out.println("TEST");
                testOrigins();
            }
        }
        String tempOs = "linux";
        Origin[] destOrigins = createDestPaths(tempOs);
        Origin[] fromOrigins = createOrigPaths(tempOs);

        Path[] writePaths = new Path[destOrigins.length];
        for (int i = 0; i < destOrigins.length; i++) {
            Path write = Paths.get(destOrigins[i].getInitArg(), destOrigins[i].getKArgs());
            System.out.println("ADDING " + write);
            writePaths[i] = write;
        }


        Path[] readPaths = new Path[fromOrigins.length];
        for (int i = 0; i < fromOrigins.length; i++) {
            Path read = Paths.get(fromOrigins[i].getInitArg(), fromOrigins[i].getKArgs());
            System.out.println("ADDING " + read);
            readPaths[i] = read;
        }
        if (readPaths.length != writePaths.length) System.exit(1);
        ApplicationManager appManager = ApplicationManager.getInstance();
        appManager.run(readPaths, writePaths);
    }
}