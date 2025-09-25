package Controller.Item;

import Model.ItemDetails;
import javafx.collections.ObservableList;

public interface ItemService {
    void addItems(ItemDetails itemDetails1);

    ObservableList<ItemDetails> getAllItems();


    void UpdateItems(String code, ItemDetails itemDetails1);

    void DeleteItem(String code);
}
