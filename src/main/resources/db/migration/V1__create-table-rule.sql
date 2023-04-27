CREATE TABLE rule (
rule_id VARCHAR NOT NULL,
rule_name VARCHAR NOT NULL,
segment VARCHAR NOT NULL,
isActive BOOLEAN NOT NULL,
PRIMARY KEY (rule_id)
);

CREATE INDEX rule_segment_index ON rule (segment);