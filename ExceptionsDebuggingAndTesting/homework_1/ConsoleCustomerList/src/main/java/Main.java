import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static Logger logger;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        logger = LogManager.getRootLogger();

        while (true) {
            String command = scanner.nextLine();
            logger.info("Введена команда: " + command);
            String[] tokens = command.split("\\s+", 2);

            if (tokens[0].equals("add")) {
                try {
                    executor.addCustomer(tokens[1]);
                } catch (ArrayIndexOutOfBoundsException ex){
                    logger.error(ex.getMessage());
                    System.out.println("Неправильный формат команды ADD");
                    ex.printStackTrace();
                } catch (IllegalArgumentException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                }
            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
            } else if (tokens[0].equals("remove")) {
                executor.removeCustomer(tokens[1]);
            } else if (tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
            } else if (tokens[0].equals("help")) {
                System.out.println(helpText);
            } else {
                System.out.println(COMMAND_ERROR);
            }
        }
    }
}
