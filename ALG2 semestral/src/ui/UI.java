package ui;

import app.Dashboard;
import utils.TextMessages;
import utils.Utils;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.Utils.*;

public class UI {

    public static Dashboard db;
    public static boolean end = false;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        db = new Dashboard();
        System.out.println(TextMessages.MainMenu());
        while (!end) {
            run();
        }
    }

    public static void run() {
        String[] parts = sc.nextLine().split(" ");
        switch (parts[0]) {
            case "help":
            case "h":
                System.out.println(TextMessages.helpMessage()); break;
            case "check": checkPf(parts); break;
            case "save": savePf(); break;
            case "buy": buyAsset(); break;
            case "sell": sellAsset(); break;
            case "load": loadPortfolio(); break;
            case "home":
                System.out.println(TextMessages.MainMenu()); break;
            case "exit": end = true; break;
            default :
                System.out.println("Neznámý příkaz! Napište 'help' nebo 'h' pro nápovědu!"); break;
        }
    }

    public static void savePf(){
        int index = 0;
        System.out.println(TextMessages.buyAsset());
        switch (sc.next()) {
            case "stocks": index = 0;
            case "crypto": index = 1;
        }
        System.out.println(TextMessages.loadPf());
        try {db.saveInfo(sc.next(), index);
        } catch (IOException e) {
            System.out.println("Soubor se nepodařilo vytvořit.\n"+e.getMessage());
        }
    }

    public static void buyAsset(){
        double deposit;
        int period;
        System.out.println(TextMessages.buyAsset());
        switch (sc.next()) {
            case "stocks":
                System.out.println(TextMessages.DepositValue());
                deposit = sc.nextDouble();
                System.out.println(TextMessages.Period());
                period = sc.nextInt();
                if(db.buy(0, deposit, period))
                {
                    bought(0);
                } else {
                    System.out.println(TextMessages.pfStocksExists());
                } break;
            case "crypto": System.out.println(TextMessages.DepositValue());
                deposit = sc.nextDouble();
                System.out.println(TextMessages.Period());
                period = sc.nextInt();
                if(db.buy(1, deposit, period))
                {
                    bought(1);
                } else {
                    System.out.println(TextMessages.pfCryptoExists());
                } break;
            default: break;
        }
    }

    public static void sellAsset() {

    }

    public static void loadPortfolio() {
        System.out.println(TextMessages.loadPf());
        try {boolean occupied = db.loadInfo(sc.next());
             if (occupied == true)
        {
            System.out.println(TextMessages.pfExists());
        }
        } catch (IOException e) {
            System.out.println("Soubor nebyl nalezen/nepodařilo se načíst.\n"+e.getMessage());
        }
    }

    public static void bought(int index) {
        List<double[]> history = db.getHistory(index);
        for (double[] arr:history
             ) {
            Utils.Wait();
            System.out.print(String.format("Uplynulé měsíce: %d Aktuální hodnota: %f ", (int)arr[0], arr[2]));
            if (arr[5] >= 0) {
                Utils.percentPos(arr[5]);
            } else {
                Utils.percentNeg(arr[5]);
            }
        }
        System.out.println(" ");
        System.out.println(db.getReport(index));
    }

    public static void checkPf(String[] parts){
        if (parts.length >= 2) {
        switch (parts[1]) {
        case "stocks": if (db.checkPortfolios()[0] == true) {
        System.out.println(db.getReport(0));
        } else {
        System.out.println(TextMessages.pfNotFound());
        } break;
        case "crypto": if (db.checkPortfolios()[1] == true) {
        System.out.println(db.getReport(1));
        } else {
        System.out.println(TextMessages.pfNotFound());
        }
    }
    }


}
}
