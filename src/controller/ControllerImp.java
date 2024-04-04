package controller;

import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ControllerImp implements Controller {
    ArrayList<Item> itemList = new ArrayList<>();
    
    @Override
    public <T> List<T> sortByName(List<T> list) {
        
        return null;
    }

    @Override
    public <T> List<T> sortByQuantity(List<T> list) {
        return null;
    }

    @Override
    public <T> List<T> searchByName(List<T> list, String key) {
        return null;
    }

}
