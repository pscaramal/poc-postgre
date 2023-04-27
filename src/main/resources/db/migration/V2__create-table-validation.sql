CREATE TABLE validation (
validation_id VARCHAR(36) NOT NULL,
rule_id VARCHAR NOT NULL,
isActive BOOLEAN NOT NULL,
path VARCHAR(255) NOT NULL,
operator VARCHAR(10) NOT NULL,
value_validation VARCHAR(1024) NOT NULL,
isValueNumber BOOLEAN NOT NULL,
isCardValidation BOOLEAN NOT NULL,
PRIMARY KEY (validation_id),
CONSTRAINT fk_rule_id
   FOREIGN KEY (rule_id)
      REFERENCES rule (rule_id)
);