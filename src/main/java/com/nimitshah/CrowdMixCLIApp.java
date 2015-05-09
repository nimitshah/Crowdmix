package com.nimitshah;

import com.nimitshah.command.CommandFactory;

import java.io.*;
import java.util.Scanner;

public class CrowdMixCLIApp {

    private Scanner scanner;

    public CrowdMixCLIApp(){
        scanner = new Scanner(System.in);
    }


    public static void main(String... a) throws IOException {
        CrowdMixCLIApp app = new CrowdMixCLIApp();
        app.start();

    }

    public void start() {
        System.out.print(">");
        while(true){
            //use command pattern, create factory to create command
            String command = scanner.nextLine();
            String res = execute(command);
            System.out.print(res);
            System.out.print(">");
        }
    }

    public String execute(String command) {
        return CommandFactory.getCommand(command).execute();
    }
}
