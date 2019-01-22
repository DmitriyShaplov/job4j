package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * @author shaplov
 * @version $Id$
 * @since 10.01.2019
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();
    boolean exit = false;

    /**
     * Constructor
     * @param input object type Input
     * @param tracker object type Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * this method fills ArrayList actions
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new Item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new Exit(6, "Exit Program"));
    }

    /**
     * Show menu items.
     */
    public void show() {
        System.out.println("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * The method for getting actions length
     *
     * @return array actions length
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * This method, depending on the specified key, performs the appropriate action.
     * @param key operation key
     * @return boolean exit flag
     */
    public boolean select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
        return !exit;
    }

    /**
     * common method for print resulting info
     * @param items array type Item
     */
    private static void printItems(Item[] items) {
        if (items.length > 0) {
            System.out.println("Here are all of the required items :");
            System.out.printf("%s  %-13s %-15s %s%n", "№", "Id", "Name", "Description");
            System.out.println("-----------------------------------------------------");
            for (int index = 0; index < items.length; index++) {
                System.out.printf("%-3d %s %-15s %s%n", index + 1, items[index].getId(),
                        items[index].getName(), items[index].getDesc());
            }
            System.out.println("-----------------------------------------------------");
        } else {
            System.out.println("------------ There are no items with these properties tracker ------------");
        }
    }

    /**
     * inner class for adding item
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding a new item ------------");
            String name = input.ask("Enter item name : ");
            String desc = input.ask("Enter item description : ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ New item with Id : " + item.getId() + " ------------");
        }
    }

    /**
     * inner class for showing items
     */
    private class ShowItems extends BaseAction {

        public ShowItems(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Showing all of the items ------------");
            Item[] items = tracker.findAll();
            printItems(items);
        }
    }

    /**
     * inner class for edit items
     */
    private class EditItem extends BaseAction {

        public EditItem(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Editing an item ------------");
            String id = input.ask("Enter the Id of the required item : ");
            String name = input.ask("Enter item new name : ");
            String desc = input.ask("Enter item new description : ");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            if (result) {
                System.out.println("------------ Items have successfully replaced ------------");
            } else {
                System.out.println("------------ There is no item with this Id ------------");
            }
        }
    }

    /**
     * inner class for deleting items
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Deleting items ------------");
            String id = input.ask("Enter the Id of deleting item : ");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.println("------------ The item has successfully deleted ------------");
            } else {
                System.out.println("------------ There is no item with this Id ------------");
            }
        }
    }

    /**
     * inner class for find item by Id
     */
    private class FindItemById extends BaseAction {

        public FindItemById(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Finding an item by Id ------------");
            String id = input.ask("Enter the Id of deleting item : ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.printf("Id: %s Name: %s Description: %s%n", item.getId(), item.getName(), item.getDesc());
                System.out.println("-----------------------------------------------------");
            } else {
                System.out.println("There is no item with this Id");
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    /**
     * inner class for find item by Id
     */
    private class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Finding items by name ------------");
            String name = input.ask("Enter the name of required items : ");
            Item[] items = tracker.findByName(name);
            printItems(items);
        }
    }

    /**
     * inner class for exit
     */
    private class Exit extends BaseAction {
        public Exit(int key, String info) {
            super(key, info);
        }

        public void execute(Input input, Tracker tracker) {
            exit = true;
        }
    }
}
