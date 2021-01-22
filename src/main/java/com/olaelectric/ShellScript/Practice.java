package com.olaelectric.ShellScript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practice {

    public void publishCommand(){

        ProcessBuilder processBuilder = new ProcessBuilder();

        // Run a shell command
        processBuilder.command("sudo","/usr/local/mysql/support-files/mysql.server","start");


        try {

            Process process = processBuilder.start();

//            StringBuilder output = new StringBuilder();
//
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//
//            long starttime = System.currentTimeMillis();
//
//
//            String line;
//            while (System.currentTimeMillis()<starttime+waittime) {
//               line = reader.readLine();
//                System.out.println(line);
//            }

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
