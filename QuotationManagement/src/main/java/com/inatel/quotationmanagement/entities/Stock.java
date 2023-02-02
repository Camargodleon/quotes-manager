package com.inatel.quotationmanagement.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "stock_app")
@Data
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length=36, columnDefinition="CHAR(36)")
    private UUID id;

    @Column(name = "stock_id", columnDefinition = "VARCHAR(255)")
    private String stockId;

    @ElementCollection
    @CollectionTable(name = "stock_quotes", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "date_key", columnDefinition = "DATE")
    @Column(name = "quote_value", columnDefinition = "DOUBLE")
    @MapKeyTemporal(TemporalType.DATE)
    private Map<Date, Double> quotes;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "id"))
//    private List<Quotes> quotes;

}
