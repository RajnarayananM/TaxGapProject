
package com.example.taxgap.domain.dto; import jakarta.validation.constraints.NotEmpty; import java.util.List; public class TransactionUploadRequest { @NotEmpty private List<TransactionUploadItem> transactions; public List<TransactionUploadItem> getTransactions(){return transactions;} public void setTransactions(List<TransactionUploadItem> v){transactions=v;} }
