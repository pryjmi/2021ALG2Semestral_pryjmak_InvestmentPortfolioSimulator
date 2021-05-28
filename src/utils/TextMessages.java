package utils;

public class TextMessages {

    public static void helpMessage(){
        System.out.println("Pro seznam příkazů napište 'help' nebo 'h'");
        System.out.println("Příkaz 'check stocks' slouží ke kontrole akcií");
        System.out.println("Příkaz 'check crypto' slouží ke kontrole kryptoměn");
        System.out.println("Příkaz 'buy' slouží k nákupu aktiv v portfoliu");
        System.out.println("Příkaz 'sell' slouží k prodeji aktiv v portfoliu");
        System.out.println("Příkaz 'load' slouží k načtení prtofolia ze souboru");
        System.out.println("Příkaz 'home' vás vrátí na domovskou obrazovku");
        System.out.println("Příkaz 'exit' ukončí program");
    }

    public static void MainMenu(){
        System.out.println("Pro seznam příkazů napište 'help' nebo 'h'");
    }

    public static void ManAcc(){
        System.out.println("Vyberte aktivum");
        System.out.println("Akcie 'stocks'");
        System.out.println("Kryptoměny 'crypto'");
    }

    public static String DepositValue(){
        return "Zadejte hodnotu vkladu.";
    }

    public static String Period(){
        return "Zadejte dobu (pouze měsíce), po kterou bude vklad uschován.";
    }

    public static String CryptoMenu(){
        return "Dále je tu možnost 'měsíční investování', kdy po každém měsíci máte možnost zda peníze vyberete/vložíte navíc a zda budete s investicí pokračovat/uzavřete obchod.\n" +
                "Vyberte typ investování.\n" +
                "Klasické (na určitou dobu) 'classic'\n" +
                "Měsíční 'month'";
    }

    /*
    public static String cryptoMonthChoice(){
        return "Vyberte jednu z následujících možností.\n"+
                "(1) Pokračovat v investici bez vkladu navíc\n"+
                "(2) Pokračovat v investici s vkladem navíc\n"+
                "(3) Uzavřít obchod";
    }
     */

    public static void savePf(){
        System.out.println("Portfolio bylo uloženo\n");
    }

}
