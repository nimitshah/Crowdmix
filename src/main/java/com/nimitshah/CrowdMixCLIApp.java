package com.nimitshah;

import java.io.*;
import java.util.Scanner;

public class CrowdMixCLIApp {

    private Scanner scanner;
    private PrintWriter writer;

    public CrowdMixCLIApp(){
        scanner = new Scanner(System.in);
        writer = new PrintWriter(new OutputStreamWriter(System.out));
    }


    public static void main(String... a) throws IOException {
        CrowdMixCLIApp app = new CrowdMixCLIApp();
        app.start();

    }

    public void start() {
        while(true){
            //use command pattern, create factory to create command
            String command = scanner.nextLine();
            execute(command);
            writer.println("Received:"+command);

        }
    }

    public String execute(String command) {

        return command;
    }
}
