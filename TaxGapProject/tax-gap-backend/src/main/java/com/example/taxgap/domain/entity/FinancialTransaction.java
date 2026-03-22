
package com.example.taxgap.domain.entity; import com.example.taxgap.domain.enums.*; import jakarta.persistence.*; import java.math.BigDecimal; import java.time.*;
@Entity @Table(name="financial_transactions")
public class FinancialTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(name="transaction_id", unique=true, nullable=false) private String transactionId;
 @Column(nullable=false) private LocalDate date; @Column(name="customer_id", nullable=false) private String customerId;
 @Column(nullable=false, precision=18, scale=2) private BigDecimal amount; @Column(name="tax_rate", nullable=false, precision=10, scale=4) private BigDecimal taxRate;
 @Column(name="reported_tax", precision=18, scale=2) private BigDecimal reportedTax; @Enumerated(EnumType.STRING) @Column(name="transaction_type", nullable=false) private TransactionType transactionType;
 @Enumerated(EnumType.STRING) @Column(name="validation_status", nullable=false) private ValidationStatus validationStatus; @Column(name="failure_reasons_json", columnDefinition="TEXT") private String failureReasonsJson;
 @Column(name="expected_tax", precision=18, scale=2) private BigDecimal expectedTax; @Column(name="tax_gap", precision=18, scale=2) private BigDecimal taxGap; @Enumerated(EnumType.STRING) @Column(name="compliance_status") private ComplianceStatus complianceStatus;
 @Column(name="original_sale_txn_id") private String originalSaleTransactionId; @Column(name="created_at") private LocalDateTime createdAt = LocalDateTime.now();
 public Long getId(){return id;} public void setId(Long id){this.id=id;} public String getTransactionId(){return transactionId;} public void setTransactionId(String v){this.transactionId=v;}
 public LocalDate getDate(){return date;} public void setDate(LocalDate v){this.date=v;} public String getCustomerId(){return customerId;} public void setCustomerId(String v){this.customerId=v;}
 public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){this.amount=v;} public BigDecimal getTaxRate(){return taxRate;} public void setTaxRate(BigDecimal v){this.taxRate=v;}
 public BigDecimal getReportedTax(){return reportedTax;} public void setReportedTax(BigDecimal v){this.reportedTax=v;} public TransactionType getTransactionType(){return transactionType;} public void setTransactionType(TransactionType v){this.transactionType=v;}
 public ValidationStatus getValidationStatus(){return validationStatus;} public void setValidationStatus(ValidationStatus v){this.validationStatus=v;} public String getFailureReasonsJson(){return failureReasonsJson;} public void setFailureReasonsJson(String v){this.failureReasonsJson=v;}
 public BigDecimal getExpectedTax(){return expectedTax;} public void setExpectedTax(BigDecimal v){this.expectedTax=v;} public BigDecimal getTaxGap(){return taxGap;} public void setTaxGap(BigDecimal v){this.taxGap=v;}
 public ComplianceStatus getComplianceStatus(){return complianceStatus;} public void setComplianceStatus(ComplianceStatus v){this.complianceStatus=v;} public String getOriginalSaleTransactionId(){return originalSaleTransactionId;} public void setOriginalSaleTransactionId(String v){this.originalSaleTransactionId=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){this.createdAt=v;}
}
