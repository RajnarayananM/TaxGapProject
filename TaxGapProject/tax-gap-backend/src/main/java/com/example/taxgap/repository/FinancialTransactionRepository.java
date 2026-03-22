
package com.example.taxgap.repository;
import com.example.taxgap.domain.entity.FinancialTransaction; import com.example.taxgap.domain.enums.TransactionType; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param; import java.util.*;
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
  Optional<FinancialTransaction> findByTransactionId(String txnId);
  @Query("select f from FinancialTransaction f where f.customerId=:customerId and f.transactionType=:type order by f.date desc")
  List<FinancialTransaction> findRecentByCustomerAndType(@Param("customerId") String customerId, @Param("type") TransactionType type);
  @Query(value="SELECT customer_id as customerId, SUM(amount) as totalAmount, SUM(COALESCE(reported_tax,0)) as totalReportedTax, SUM(COALESCE(expected_tax,0)) as totalExpectedTax, SUM(COALESCE(tax_gap,0)) as totalTaxGap, (100 - (SUM(CASE WHEN compliance_status <> 'COMPLIANT' OR compliance_status IS NULL THEN 1 ELSE 0 END)/ NULLIF(COUNT(*),0) * 100)) as complianceScore FROM financial_transactions GROUP BY customer_id", nativeQuery=true)
  List<Object[]> aggregateCustomerSummary();
}
