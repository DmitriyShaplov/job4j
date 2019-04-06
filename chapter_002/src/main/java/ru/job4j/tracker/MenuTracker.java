package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author shaplov
 * @version $Id$
 * @since 10.01.2019
 */
public class MenuTracker {
    private Input input;
    private ITracker tracker;
    private ArrayList<UserAction> actions = new ArrayList<>();
    boolean exit = false;
    private final Consumer<String> output;

    /**
     * Constructor
     * @param input object type Input
     * @param tracker object type Tracker
     */
    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
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
        output.accept("Menu.");
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
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
    private void printItems(List<Item> items) {
        if (items.size() > 0) {
            output.accept("Here are all of the required items :");
            output.accept(String.format("%s  %-13s %-15s %s", "№", "Id", "Name", "Description"));
            output.accept("-----------------------------------------------------");
            for (int index = 0; index < items.size(); index++) {
                output.accept(String.format("%-3d %s %-15s %s", index + 1, items.get(index).getId(),
                        items.get(index).getName(), items.get(index).getDesc()));
            }
            output.accept("-----------------------------------------------------");
        } else {
            output.accept("------------ There are no items with these properties tracker ------------");
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
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Adding a new item ------------");
            String name = input.ask("Enter item name : ");
            String desc = input.ask("Enter item description : ");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("------------ New item with Id : " + item.getId() + " ------------");
        }
    }

    /**
     * inner class for showing items
     */
    private class ShowItems extends BaseAction {

        public ShowItems(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Showing all of the items ------------");
            List<Item> items = tracker.findAll();
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

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Editing an item ------------");
            String id = input.ask("Enter the Id of the required item : ");
            String name = input.ask("Enter item new name : ");
            String desc = input.ask("Enter item new description : ");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            if (result) {
                output.accept("------------ Items have successfully replaced ------------");
            } else {
                output.accept("------------ There is no item with this Id ------------");
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

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Deleting items ------------");
            String id = input.ask("Enter the Id of deleting item : ");
            boolean result = tracker.delete(id);
            if (result) {
                output.accept("------------ The item has successfully deleted ------------");
            } else {
                output.accept("------------ There is no item with this Id ------------");
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

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Finding an item by Id ------------");
            String id = input.ask("Enter the Id of deleting item : ");
            Item item = tracker.findById(id);
            if (item != null) {
                output.accept(String.format("Id: %s Name: %s Description: %s", item.getId(), item.getName(), item.getDesc()));
                output.accept("-----------------------------------------------------");
            } else {
                output.accept("There is no item with this Id");
                output.accept("-----------------------------------------------------");
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

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Finding items by name ------------");
            String name = input.ask("Enter the name of required items : ");
            List<Item> items = tracker.findByName(name);
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

        @Override
        public void execute(Input input, ITracker tracker) {
            exit = true;
        }
    }
}
