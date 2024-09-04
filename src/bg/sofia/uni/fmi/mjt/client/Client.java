package bg.sofia.uni.fmi.mjt.client;

import bg.sofia.uni.fmi.mjt.client.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import static bg.sofia.uni.fmi.mjt.client.commands.CommandType.DISCONNECT;
import static bg.sofia.uni.fmi.mjt.client.commands.CommandType.UNKNOWN;
import static bg.sofia.uni.fmi.mjt.client.commands.CommandType.HELP;

public class Client {
    private static final int SERVER_PORT = 8888;
    private static final String HOST_NAME = "localhost";
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open();
             BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, CHARSET));
             PrintWriter writer = new PrintWriter(Channels.newWriter(socketChannel, CHARSET), true);
             Scanner scanner = new Scanner(System.in)) {

            socketChannel.connect(new InetSocketAddress(HOST_NAME, SERVER_PORT));

            System.out.println("Connected to the server.");
            Command.printMenu();

            while (true) {
                System.out.print("Enter command: ");
                String consoleInput = scanner.nextLine(); // read a line from the console

                Command command = new Command(consoleInput);

                while (command.getType() == UNKNOWN) {
                    System.out.println("Unknown command! For more information enter: help");

                    consoleInput = scanner.nextLine();
                    command.update(consoleInput);
                }

                if (command.getType() == DISCONNECT) {
                    System.out.println("Disconnected from the server");
                    break;
                }
                if (command.getType() == HELP) {
                    command.printMenuDescription();
                    continue;
                }

                writer.println(consoleInput);

                String reply = reader.readLine(); // read the response from the server
                formatServerReply(reply);
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to the server. Try again later.");
        }
    }

    private static void formatServerReply(String reply) {
        String result = reply.replaceAll("#", "\n");

        System.out.println(result);
    }
}
