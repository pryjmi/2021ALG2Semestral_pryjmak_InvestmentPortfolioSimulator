package utils;

import app.Portfolio;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static final Scanner sc = new Scanner(System.in);
    public static int choice;
    public static String menuChoice;
    private static final Portfolio pf = new Portfolio();
    public static final Portfolio Crypto = new Portfolio();
    public static final Portfolio Stocks = new Portfolio();

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void Wait() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String Description() {
        return "Vybrali jste si " + pf.getName().toLowerCase() + ". Při investování se vklad uschová na vámi zvolenou dobu, při které není možné peníze vybrat, či vložit navíc.";
    }

    public static void dealClosedStocks() {
        showInfoStocks();
        System.out.println("Obchod uzavřen.");
    }

    public static void dealClosedCrypto() {
        showInfoCrypto();
        System.out.println("Obchod uzavřen.");
    }

    public static void percentCondTrue() {
        System.out.println("Asset value changed by " + ANSI_RED + ((pf.getRandomPercent() - 1) * 100) + " %" + ANSI_RESET + "\n");
    }

    public static void percentCondFalse() {
        System.out.println("Asset value changed by " + ANSI_GREEN + ((pf.getRandomPercent() - 1) * 100) + " %" + ANSI_RESET + "\n");
    }

    public static String showBeginStockValue() {
        return "Hodnota jednoho aktiva na počátku investice: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getStartStockValue() + ANSI_RESET;
    }

    public static String showBeginCryptoValue() {
        return "Hodnota jednoho aktiva na počátku investice: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getStartCryptoValue() + ANSI_RESET;
    }

    public static String showBeginStockQuantity() {
        return "Počet aktiv na počátku investice: " + ANSI_BLUE + pf.getStartStockQuantity() + ANSI_RESET;
    }

    public static String showBeginCryptoQuantity() {
        return "Počet aktiv na počátku investice: " + ANSI_BLUE + pf.getStartCryptoQuantity() + ANSI_RESET;
    }

    public static String showBeginDeposit() {
        return "Počáteční vklad: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getStartDeposit() + ANSI_RESET;
    }

    public static String showActualStockValue() {
        return "Aktuální hodnota jednoho aktiva: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getActStockValue() + ANSI_RESET;
    }

    public static String showActualCryptoValue() {
        return "Aktuální hodnota jednoho aktiva: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getActCryptoValue() + ANSI_RESET;
    }

    public static String showActualStockQuantity() {
        return "Aktuální počet aktiv: " + ANSI_BLUE + pf.getActStockQuantity() + ANSI_RESET;
    }

    public static String showActualCryptoQuantity() {
        return "Aktuální počet aktiv: " + ANSI_BLUE + pf.getActCryptoQuantity() + ANSI_RESET;
    }

    public static String showActualStockInvestmentValue() {
        return "Aktuální hodnota investice: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getActStockSum() + ANSI_RESET;
    }

    public static String showActualCryptoInvestmentValue() {
        return "Aktuální hodnota investice: " + ANSI_PURPLE + "$ " + ANSI_BLUE + pf.getActCryptoSum() + ANSI_RESET;
    }

    public static String showProfitStocks() {
        if (pf.getProfitPercentStock() < 0) {
            return "Celkový Zisk/Ztráta: " + ANSI_RED + "$ " + pf.getProfitStock() + " (" + pf.getProfitPercentStock() + " %)" + ANSI_RESET;
        } else {
            return "Celkový Zisk/Ztráta: " + ANSI_GREEN + "$ " + pf.getProfitStock() + " (" + pf.getProfitPercentStock() + " %)" + ANSI_RESET;
        }
    }

    public static String showProfitCrypto() {
        if (pf.getProfitPercentCrypto() < 0) {
            return "Celkový Zisk/Ztráta: " + ANSI_RED + "$ " + pf.getProfitCrypto() + " (" + pf.getProfitPercentCrypto() + " %)" + ANSI_RESET;
        } else {
            return "Celkový Zisk/Ztráta: " + ANSI_GREEN + "$ " + pf.getProfitCrypto() + " (" + pf.getProfitPercentCrypto() + " %)" + ANSI_RESET;
        }
    }

    public static void percentCondition() {
        if (pf.getRandomPercent() < 1) {
            Utils.percentCondTrue();
        } else {
            Utils.percentCondFalse();
        }
    }

    public static void showInfoStocks(){
        System.out.println(pf.getName()+"\n"+ showBeginStockValue()+"\n"+ showBeginStockQuantity()+"\n"+showBeginDeposit()+"\n"
                + showActualStockValue()+"\n"+ showActualStockQuantity()+"\n"+ showActualStockInvestmentValue()+"\n"+ showProfitStocks()+"\n");
    }

    public static void showInfoCrypto(){
        System.out.println(pf.getName()+"\n"+ showBeginCryptoValue()+"\n"+ showBeginCryptoQuantity()+"\n"+showBeginDeposit()+"\n"
                + showActualCryptoValue()+"\n"+ showActualCryptoQuantity()+"\n"+ showActualCryptoInvestmentValue()+"\n"+ showProfitCrypto()+"\n");
    }

    public static void saveStocksPf(String name, double AssetValue, double AssetQuantity, double Sum) {
            if (AssetValue != 0) {
                System.out.println(pf.getStocksInfo());
                System.out.println("Příkazem 'save' uložíte portfolio do externího souboru");
            } else {
                System.out.println("Akciové portfolio nebylo vytvořeno/načteno");
            }
    }

    public static void saveCryptoPf(String name, double AssetValue, double AssetQuantity, double Sum){
        if (AssetValue != 0) {
            System.out.println(pf.getCryptoInfo());
            System.out.println("Příkazem 'save' uložíte portfolio do externího souboru");
        } else {
            System.out.println("Kryptoměnové portfolio nebylo vytvořeno/načteno");
        }
    }
}
