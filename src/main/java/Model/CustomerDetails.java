package Model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDetails {
    private String id;
    private String title;
    private String name;
    private LocalDate dob;
    private double salary;
    private String address;
    private String city;
    private  String province;
    private int postalcode;
}
