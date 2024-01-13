package com.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Audited
@AuditTable(value = "H$customer")
@DynamicUpdate
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(generator = "MY_OWN_CUSTOMER_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "MY_OWN_CUSTOMER_SEQ", sequenceName = "MY_OWN_CUSTOMER_SEQ")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "name")
    private String customerName;

    private String email;

    @Column(name = "mobile_number", updatable = false)
    private String mobileNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return customerId.hashCode();
    }
}
