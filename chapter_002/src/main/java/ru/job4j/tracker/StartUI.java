package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 27.12.2018
 */
public class StartUI {

    private static final String ADD = "0";

    private static final String SHOW_ALL = "1";

    private static final String EDIT = "2";

    private static final String DELETE = "3";

    private static final String FIND_BY_ID = "4";

    private static final String FIND_BY_NAME = "5";

    private static final String EXIT = "6";

    private final Input input;

    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Main program loop
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = input.ask("Please, enter menu item number : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * method implements adding a new item to tracker
     */
    private void createItem() {
        System.out.println("------------ Adding a new item ------------");
        String name = this.input.ask("Enter item name : ");
        String desc = this.input.ask("Enter item description : ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ New item with Id : " + item.getId() + " ------------");
    }

    /**
     * method showing all the items from tracker
     */
    private void showItems() {
        System.out.println("------------ Showing all of the items ------------");
        Item[] items = this.tracker.findAll();
        this.printItems(items);
    }

    /**
     * method editing an item by replacing with new one
     */
    private void editItem() {
        System.out.println("------------ Editing an item ------------");
        String id = this.input.ask("Enter the Id of the required item : ");
        String name = this.input.ask("Enter item new name : ");
        String desc = this.input.ask("Enter item new description : ");
        Item item = new Item(name, desc);
        boolean result = this.tracker.replace(id, item);
        if (result) {
            System.out.println("------------ Items have successfully replaced ------------");
        } else {
            System.out.println("------------ There is no item with this Id ------------");
        }
    }

    /**
     * method deleting an item from the tracker
     */
    private void deleteItem() {
        System.out.println("------------ Deleting items ------------");
        String id = this.input.ask("Enter the Id of deleting item : ");
        boolean result = this.tracker.delete(id);
        if (result) {
            System.out.println("------------ The item has successfully deleted ------------");
        } else {
            System.out.println("------------ There is no item with this Id ------------");
        }
    }

    /**
     * this method showing one item by Id
     */
    private void findItemById() {
        System.out.println("------------ Finding an item by Id ------------");
        String id = this.input.ask("Enter the Id of deleting item : ");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.printf("Id: %s\nName: %s Description: %s\n", item.getId(), item.getName(), item.getDesc());
            System.out.println("------------------------------------------------------");
        } else {
            System.out.println("There is no item with this Id");
            System.out.println("------------------------------------------------------");
        }
    }

    private void findItemsByName() {
        System.out.println("------------ Finding items by name ------------");
        String name = this.input.ask("Enter the name of required items : ");
        Item[] items = this.tracker.findByName(name);
        this.printItems(items);
    }

    private void printItems(Item[] items) {
        if (items.length > 0) {
            System.out.println("Here are all of the required items :");
            System.out.println("№\tId\tName\tDescription");
            System.out.println("-----------------------------------------------------");
            for (int index = 0; index < items.length; index++) {
                System.out.printf("%d %-5s %-11s %-23s\n", index, items[index].getId(),
                        items[index].getName(), items[index].getDesc());
            }
            System.out.println("------------------------------------------------------");
        } else {
            System.out.println("------------ There are no items with these properties tracker ------------");
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Run program
     * @param args cmd args.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
