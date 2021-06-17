package utils;

import app.Crypto;
import app.Stocks;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class FileOperations {

    public static Portfolio loadFile(String fileName) throws IOException {
        if (fileName.endsWith(".txt")) {
            return loadTxt(fileName);
        } else if (fileName.endsWith(".csv")) {
            return loadCsv(fileName);
        } else {
            return loadBin(fileName);
        }
    }

    private static Portfolio loadTxt(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            String name = line.split(" ")[0];
            br.readLine();
            String[] parts = line.split(" ");
            double[] array = {Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2])};
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                case "AKCIE":
                    return new Stocks(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
            }
            return null;
        }
    }

    private static Portfolio loadCsv(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line = br.readLine();
            String name = line.split(",")[0];
            br.readLine();
            String[] parts = line.split(",");
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
                case "AKCIE":
                    return new Stocks(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), Integer.parseInt(parts[6]), Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
            }
            return null;
        }
    }

    private static Portfolio loadBin(String fileName) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (DataInputStream bin = new DataInputStream(new FileInputStream(f))) {
            String name = bin.readUTF();
            bin.readInt();
            bin.readInt();
            bin.readInt();
            bin.readLong();
            switch (name) {
                case "KRYPTOMĚNA":
                    return new Crypto(bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readInt(), bin.readDouble(), bin.readDouble());
                case "AKCIE":
                    return new Stocks(bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readDouble(), bin.readInt(), bin.readDouble(), bin.readDouble());
            }
            return null;
        }
    }

    public static void SaveInfo(String fileName, Portfolio port) throws IOException {
        switch (fileName.substring(fileName.length() - 4)) {
            case ".txt":
                saveTxt(fileName, port); break;
            case ".csv":
                saveCsv(fileName, port); break;
            default:
                saveBin(fileName, port); break;
        }
    }

    private static void saveTxt(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            pw.println(port.getName() + " " + LocalTime.now().toString() + " " + LocalDate.now().toString());
            pw.println(String.format("%15s %15s %15s %15s %15s %15s %7s %10s %4s", "Akt. Hodnota Aktiva", "Akt. Počet Aktiv", "Akt. Hod. Investice", "Počát. Hod. Aktiva", "Počát. Počet Aktiv", "Počát. Hod. Investice", "Měsíc", "Zisk/Ztráta", "%"));
            pw.println(String.format("%15f %15f %15f %15f %15f %15f %7d %10f %4f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getProfit(), port.getProfitPercent()));
        }
    }

    private static void saveCsv(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            pw.println(port.getName() + "," + LocalTime.now().toString() + "," + LocalDate.now().toString());
            pw.println("Akt. Hodnota Aktiva,Akt. Počet Aktiv,Akt. Hod. Investice,Počát. Hod. Aktiva,Počát. Počet Aktiv,Počát. Hod. Investice,Měsíc,Zisk/Ztráta,%");
            pw.println(String.format("%f,%f,%f,%f,%f,%f,%d,%f,%f", port.getActValue(), port.getActQuantity(), port.getActSum(), port.getStartValue(), port.getStartQuantity(), port.getStartDeposit(), port.getMonth(), port.getProfit(), port.getProfitPercent()));
        }
    }

    private static void saveBin(String fileName, Portfolio port) throws IOException {
        File f = new File(System.getProperty("user.dir") + File.separator + "Data" + File.separator + fileName);
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(f))) {
            out.writeUTF(port.getName());
            out.writeLong(LocalTime.now().toSecondOfDay());
            out.writeInt(LocalDate.now().getDayOfMonth());
            out.writeInt(LocalDate.now().getMonthValue());
            out.writeInt(LocalDate.now().getYear());
            out.writeDouble(port.getActValue());
            out.writeDouble(port.getActQuantity());
            out.writeDouble(port.getActSum());
            out.writeDouble(port.getStartValue());
            out.writeDouble(port.getStartQuantity());
            out.writeDouble(port.getStartDeposit());
            out.writeInt(port.getMonth());
            out.writeDouble(port.getProfit());
            out.writeDouble(port.getProfitPercent());

        }
    }
}
