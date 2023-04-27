-- Primeira rule
INSERT INTO rule (rule_id, rule_name, segment, isActive)
VALUES ('48afc457-a958-47b1-8c25-dc5a79a2e739', 'Regra Reprovação BR posto_berimbau', 'BR', true);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('83fe2fcf-bccb-46d7-85e7-f080205a75e1', '48afc457-a958-47b1-8c25-dc5a79a2e739', true, 'merchant.merchantId', '==', 'aa839b1b-2d13-4317-925f-f8ffa38bbcda', false, false);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('a4218ead-4c24-426e-bd4a-567b38b51031', '48afc457-a958-47b1-8c25-dc5a79a2e739', true, 'payment.paymentMethods.creditCard.bin', '=~', '498431', false, true);

-- Segunda rule
INSERT INTO rule (rule_id, rule_name, segment, isActive)
VALUES ('35eaca26-437a-4d6a-b188-1a1995662ab3', 'Regra Reprovação BR Posto Tangará', 'BR', true);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('b1fdc4ba-5801-46f6-b82a-790ea9f031bc', '35eaca26-437a-4d6a-b188-1a1995662ab3', true, 'merchant.merchantId', '==', 'afee5a00-8447-43d7-9c3f-59572172117c', false, false);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('72e11b92-48ff-46f5-b445-9443ecd8be1d', '35eaca26-437a-4d6a-b188-1a1995662ab3', true, 'payment.paymentMethods.creditCard.bin', '=~', '498431,498408,498406', false, true);

-- Terceira rule
INSERT INTO rule (rule_id, rule_name, segment, isActive)
VALUES ('fd2d64d3-339d-4d8a-8981-4cee6875a2ca', 'Regra Reprovação BR Posto Piata', 'LASA', true);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('c06dd8d8-ed10-4cbd-a607-711371f0a9df', 'fd2d64d3-339d-4d8a-8981-4cee6875a2ca', true, 'merchant.merchantId', '==', '4591fb90-44fc-407a-b51f-47ebc8d013a8', false, false);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('aef2cee2-df38-43b3-90a8-6b05ffada0dd', 'fd2d64d3-339d-4d8a-8981-4cee6875a2ca', true, 'payment.paymentInstrumentVerification.otherAuthenticationMethod', '==', 'APPROVED_ACESSO_BIO', false, false);

INSERT INTO validation(validation_id, rule_id, isActive, path, operator, value_validation, isValueNumber, isCardValidation)
VALUES ('4bcc1a56-8993-45c8-a896-3b9e06e5da6b', 'fd2d64d3-339d-4d8a-8981-4cee6875a2ca', true, 'orderDetail.totalAmount.amountLocalCurrency', '>=', '300', true, false);