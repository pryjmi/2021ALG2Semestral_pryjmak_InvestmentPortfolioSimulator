package ui;

import utils.TextMessages;
import utils.Utils;

import static utils.Utils.*;

public class UI {

    public void beginProgram() {
        TextMessages.MainMenu();
        do {
            menuChoice = sc.nextLine();
            switch (menuChoice) {
                case "check stocks": {
                    saveStocksPf(Stocks.getName(), Stocks.getActStockValue(), Stocks.getActStockQuantity(), Stocks.getActStockSum());
                    String saveChoice = sc.nextLine();
                    switch (saveChoice) {
                        case "save": {
                            System.out.println("NOT IMPLEMENTED YET");
                            break;
                        }
                    }
                    break;
                }
                case "check crypto": {
                    saveCryptoPf(Crypto.getName(), Crypto.getActCryptoValue(), Crypto.getActCryptoQuantity(), Crypto.getActCryptoSum());
                    String saveChoice = sc.nextLine();
                    switch (saveChoice) {
                        case "save": {
                            System.out.println("NOT IMPLEMENTED YET");
                        }
                    }
                    break;
                }
                case "buy": {
                    manualAcc();
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
                    TextMessages.helpMessage();
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

    private void manualAcc() {
        TextMessages.ManAcc();
            menuChoice = sc.nextLine();
            switch (menuChoice) {
                case "stocks": {
                    UIStocks Stocks = new UIStocks();
                    Stocks.manAccStocks();
                    break;
                }
                case "crypto": {
                    UICrypto Crypto = new UICrypto();
                    Crypto.manAccCrypto();
                    break;
                }
                default: {
                    break;
                }
            }
        System.out.println("Příkazem 'home' se dostanete zpět na domovskou obrazovku");
        Utils.Wait();
    }

}
