package cl.acn.lab.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ACN-labs
 * Date: 09-08-20
 */

@Data
@Entity
@Table(name = "PERSONA")
public class Person {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE", nullable = false)
    private String name;

    @Column(name = "APELLIDO_MATERNO", nullable = false)
    private String firstName;

    @Column(name = "APELLIDO_PATERNO", nullable = false)
    private String lastName;


}
