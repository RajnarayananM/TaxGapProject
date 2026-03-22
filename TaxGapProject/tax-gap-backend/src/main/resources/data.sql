
INSERT IGNORE INTO roles (id, name) VALUES (1,'ROLE_ADMIN'), (2,'ROLE_AUDITOR');
INSERT IGNORE INTO tax_rules (name, type, description, enabled, severity, config_json) VALUES
 ('HighValueTransaction','HIGH_VALUE','Flag when amount exceeds threshold', true,'HIGH', JSON_OBJECT('threshold', 100000.00)),
 ('RefundValidation','REFUND_VALIDATION','Refund must not exceed original sale amount. If originalSaleTransactionId not provided, fallback to last sale.', true,'MEDIUM', JSON_OBJECT('fallbackDays', 180)),
 ('GstSlabViolation','GST_SLAB','Ensure taxRate respects amount slabs', true,'MEDIUM', JSON_OBJECT('slabs', JSON_ARRAY(
     JSON_OBJECT('min',0,'max',5000,'rate',0.05),
     JSON_OBJECT('min',5000,'max',100000,'rate',0.12),
     JSON_OBJECT('min',100000,'max',null,'rate',0.18)
  )));
