package org.ispirer.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomList<T> extends ArrayList<T>{
    private final List<ChangeHandler<T>> changeHandlers;

    public CustomList() {
        super();
        changeHandlers = new ArrayList<>();
    }
    public CustomList(Collection<? extends T> c) {
        super(c);
        changeHandlers = new ArrayList<>();
    }

    @Override
    public boolean add(T item) {
        boolean result = super.add(item);
        if(result) onChange("add",item);
        return result;
    }
    @Override
    public void add(int index, T item) {
        super.add(index, item);
        onChange("addByIndex",item);
    }
    @Override
    public T remove(int index) {
        T removedItem = super.remove(index);
        onChange("remove",removedItem);
        return removedItem;
    }

    public void addChangeHandler(ChangeHandler<T> changeHandler) {
        changeHandlers.add(changeHandler);
    }
    public void removeChangeHandler(ChangeHandler<T> changeHandler) {
        changeHandlers.remove(changeHandler);
    }
    private void onChange(String nameEvent, T changedItem) {
        if (changeHandlers != null) {
            for (ChangeHandler<T> handler : changeHandlers) {
                handler.onChange(nameEvent, changedItem);
            }
        }
    }
    public interface ChangeHandler<T> {
        void onChange(String nameEvent, T changedItem);
    }
}
