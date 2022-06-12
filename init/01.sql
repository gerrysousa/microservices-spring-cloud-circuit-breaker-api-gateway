CREATE DATABASE IF NOT EXISTS `fornecedor`;
CREATE DATABASE IF NOT EXISTS `loja`;
CREATE DATABASE IF NOT EXISTS `transportador`;

GRANT ALL ON `fornecedor`.* TO 'user'@'%';
GRANT ALL ON `loja`.* TO 'user'@'%';
GRANT ALL ON `transportador`.* TO 'user'@'%';