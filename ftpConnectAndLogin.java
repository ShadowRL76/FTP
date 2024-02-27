import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

import java.util.HashMap;
import java.util.Scanner;

public class ftpConnectAndLogin {

    private static HashMap<String, String> commands = new HashMap<>();
    private static String serverIP;
    private static int serverPort;
    private static String username;
    private static String password;

    public static void main(String[] args) {

        System.out.println("********************************************");
        System.out.println("*                                          *");
        System.out.println("*      Welcome to the FTP Service!         *");
        System.out.println("*                                          *");
        System.out.println("********************************************");
        System.out.println();

        commands.put("-h", "\t\tDisplay help information");
        commands.put("--help", "\tDisplay help information");
        commands.put("-login", "\tLog in to FTP server");
        commands.put("-ip", "\t\tSpecify the Server IP");
        commands.put("-IP", "\t\tSpecify the Server IP");
        commands.put("-p", "\t\tSpecify the Server Port");
        commands.put("-port", "\tSpecify the Server Port");
        commands.put("-u", "\t\tSpecify the Server username");
        commands.put("-P", "\t\tSpecify the Server password");
        commands.put("-pass","\tSpecify the Server password");
        commands.put("-user","\tSpecify the Server password");
        commands.put("-U", "\t\tUploads files to FTP Server");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type in -h for a list of commands");
        boolean notLoggedIn = true;

        while (notLoggedIn) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] arguments = input.split("\\s+", 2);
            String command = arguments[0];


            switch (command) {
                case "-help":
                case "-h":
                    displayHelp();
                    break;
                case "-login":
                    login(serverIP, serverPort, username, password);
                    notLoggedIn = false;
                    break;
                case "-ip":
                case "-IP":
                    if(arguments.length > 1){
                        serverIP = arguments[1];
                        System.out.println("Server IP set to: " + serverIP);
                    }
                    else{
                        System.out.println("Please provide a server IP.");
                    }
                    break;
                case "-p":
                case "-port":
                    if(arguments.length > 1){
                        serverPort = Integer.parseInt(arguments[1]);
                        System.out.println("Server port set to: " + serverPort);
                    }
                    else{
                        System.out.println("Please provide a server port.");
                    }

                    break;
                case "-u":
                case "-user":
                    if(arguments.length > 1){
                        username = arguments[1];
                        System.out.println("Server username set to: " + username);
                    }
                    else{
                        System.out.println("Please provide a server username.");
                    }
                    break;
                case "-P":
                case "-pass":
                    if(arguments.length > 1){
                        password = arguments[1];
                        System.out.println("Server password set to: " + password);
                    }
                    else{
                        System.out.println("Please provide a server password.");
                    }
                    break;
                default:
                    System.out.println("Unrecognized command: " + command);
                    break;
            }
        }
    }

    private static void displayHelp() {
        System.out.println("Available commands:");
        for (String cmd : commands.keySet()) {
            System.out.println(cmd + "\t\t" + commands.get(cmd));
        }
    }

    private static void login(String serverIP, int serverPort, String username, String password){
        if(serverIP == null || serverPort == 0 || username == null || password == null){
            System.out.println("Must include all arguments!");
        } else {
            final int timeout = 3000;
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.setConnectTimeout(timeout);
                ftpClient.connect(serverIP, serverPort);
                boolean success = ftpClient.login(username, password);
                if(success){
                    System.out.println("Server logged in");
                }else{
                    System.out.println("Server failed to log in and connect");
                }
            }catch (IOException e) {
                System.out.println("Failed to connect");
            }
        }
    }
}








