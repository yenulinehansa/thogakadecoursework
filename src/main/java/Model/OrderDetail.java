package Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetail {
    private String orderId;
    private String itemCode;
    private int qty;
    private double discount;

}