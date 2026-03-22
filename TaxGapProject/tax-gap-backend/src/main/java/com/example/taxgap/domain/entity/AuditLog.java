
package com.example.taxgap.domain.entity; import com.example.taxgap.domain.enums.EventType; import jakarta.persistence.*; import java.time.*;
@Entity @Table(name="audit_logs")
public class AuditLog { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Enumerated(EnumType.STRING) private EventType eventType; private String transactionId; private LocalDateTime createdAt = LocalDateTime.now(); @Column(name="detail_json", columnDefinition="JSON") private String detailJson;
 public Long getId(){return id;} public void setId(Long v){id=v;} public EventType getEventType(){return eventType;} public void setEventType(EventType v){eventType=v;} public String getTransactionId(){return transactionId;} public void setTransactionId(String v){transactionId=v;} public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;} public String getDetailJson(){return detailJson;} public void setDetailJson(String v){detailJson=v;} }
