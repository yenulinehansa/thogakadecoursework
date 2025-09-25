package Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDetails {
    private String code;
    private String description;
    private String size;
    private double price;
    private int qty;
}
