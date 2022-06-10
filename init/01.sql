CREATE DATABASE IF NOT EXISTS `fornecedor`;
CREATE DATABASE IF NOT EXISTS `loja`;

GRANT ALL ON `fornecedor`.* TO 'user'@'%';
GRANT ALL ON `loja`.* TO 'user'@'%';