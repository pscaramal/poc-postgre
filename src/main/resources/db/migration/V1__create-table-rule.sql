CREATE TABLE IF NOT EXISTS rule (
rule_id VARCHAR NOT NULL,
rule_name VARCHAR NOT NULL,
segment VARCHAR NOT NULL,
isActive BOOLEAN NOT NULL,
PRIMARY KEY (rule_id)
);

CREATE INDEX IF NOT EXISTS rule_segment_index ON rule (segment);