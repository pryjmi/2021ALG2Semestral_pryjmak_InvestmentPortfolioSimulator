package ui;

import app.Space;
import utils.TextMessages;
import utils.Utils;

import static utils.Utils.*;

public class UI {

    public void beginProgram() {
        System.out.println(TextMessages.MainMenu());
        Space assets = new Space();
        do {
            menuChoice = sc.nextLine();
            switch (menuChoice) {
                case "check": {
                    assets.checkPortfolios(); break;
                }
                case "buy": {
                    buyAsset();
                    break;
                }
                case "sell": {
                    System.out.println("NOT IMPLEMENTED YET");
                    break;
                }
                case "load": {
                    System.out.println("NOT IMPLEMENTED YET");
                    break;
                }
                case "h":
                case "help": {
                    System.out.println(TextMessages.helpMessage());
                    break;
                }
                case "home": {
                    beginProgram();
                    break;
                }
                default:
                    break;
            }
        } while (!menuChoice.equals("exit"));
    }

    private void buyAsset() {
        TextMessages.buyAsset();
        Space assets = new Space();
        choice = sc.nextInt();
        double deposit = sc.nextDouble();
        int months = sc.nextInt();
        assets.buy(choice, deposit, months);
        System.out.println("Příkazem 'home' se dostanete zpět na domovskou obrazovku");
        Utils.Wait();
    }

}
