package ru.job4j.pack;

/**
 * @author shaplov
 * @since 22.03.2019
 * @version 1.00
 */
public class StartPack {

    /**
     * Run program.
     * @param args cmd args.
     */
    public static void main(String[] args) {
        Archiver archiver = new Archiver(args);
        if (archiver.createPack()) {
            System.out.println("Build Successful.");
        } else {
            System.out.println("Build failed.");
        }
    }
}
