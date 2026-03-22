
CREATE TABLE IF NOT EXISTS roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS users_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id)
);
CREATE TABLE IF NOT EXISTS tax_rules (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  type VARCHAR(50) NOT NULL,
  description VARCHAR(500),
  enabled BOOLEAN NOT NULL DEFAULT TRUE,
  severity VARCHAR(20) NOT NULL,
  config_json JSON NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS financial_transactions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  transaction_id VARCHAR(100) NOT NULL,
  date DATE NOT NULL,
  customer_id VARCHAR(100) NOT NULL,
  amount DECIMAL(18,2) NOT NULL,
  tax_rate DECIMAL(10,4) NOT NULL,
  reported_tax DECIMAL(18,2),
  transaction_type VARCHAR(20) NOT NULL,
  validation_status VARCHAR(20) NOT NULL,
  failure_reasons_json TEXT,
  expected_tax DECIMAL(18,2),
  tax_gap DECIMAL(18,2),
  compliance_status VARCHAR(20),
  original_sale_txn_id VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uk_txn (transaction_id)
);
CREATE TABLE IF NOT EXISTS exception_records (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  transaction_id VARCHAR(100) NOT NULL,
  customer_id VARCHAR(100) NOT NULL,
  rule_name VARCHAR(100) NOT NULL,
  severity VARCHAR(20) NOT NULL,
  message VARCHAR(1000) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS audit_logs (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  event_type VARCHAR(50) NOT NULL,
  transaction_id VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  detail_json JSON
);
CREATE INDEX idx_ft_customer ON financial_transactions(customer_id);
CREATE INDEX idx_ft_date ON financial_transactions(date);
CREATE INDEX idx_ft_type ON financial_transactions(transaction_type);
CREATE INDEX idx_exc_customer ON exception_records(customer_id);
CREATE INDEX idx_exc_rule ON exception_records(rule_name);
CREATE INDEX idx_exc_severity ON exception_records(severity);
