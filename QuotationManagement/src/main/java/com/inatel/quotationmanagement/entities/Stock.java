package com.inatel.quotationmanagement.entities;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ApiModel(value = "Stock", description = "Represents a stock and it's Quotes.")
public class Stock {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length=36, columnDefinition="CHAR(36)")
    @ApiModelProperty(value = "id", example = "c01cede4-cd45-11eb-b8bc-0242ac130003", access = "private", dataType = "UUID", notes = "Unique ID for this register.")
    @NotNull
    private UUID id;

    @Column(name = "stock_id", columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(name = "Stock ID", dataType = "String", access = "private", example = "petr4", notes = "String representing a stock.")
    @NotNull
    private String stockId;

    @ElementCollection
    @CollectionTable(name = "stock_quotes", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "date_key", columnDefinition = "DATE")
    @Column(name = "quote_value", columnDefinition = "DOUBLE")
    @MapKeyTemporal(TemporalType.DATE)
    @ApiModelProperty(notes = "Key-value map representing the Quotes registered for this Stock.", value = "quotes",name = "Quotes", dataType = "Map<Date, Double>", access = "private", example = "{ \"2019-01-01\": \"10\", \"2019-01-02\": \"12\", \"2019-01-03\": \"14\"}")
    @NotNull
    private Map<Date, Double> quotes;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "id"))
//    private List<Quotes> quotes;

}
