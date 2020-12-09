package org.spring.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name = "Dual")
@Table(name = "SYS.DUAL")
public class Dual {
    @Id
    private String current_time;
}
