
package com.example.taxgap.domain.entity; import jakarta.persistence.*;
@Entity @Table(name="roles")
public class Role { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true, nullable=false) private String name; public Long getId(){return id;} public void setId(Long v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} }
