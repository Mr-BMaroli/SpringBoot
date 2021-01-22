package com.olaelectric.ShellScript;

import java.io.IOException;

public class Scripting {

    public void publishCommand(){

        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run a shell command
        processBuilder.command("sudo","/usr/local/mysql/support-files/mysql.server","start");


        try {

            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.exit(90);
            } else {
                System.out.println("failure");
                System.exit(80);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
