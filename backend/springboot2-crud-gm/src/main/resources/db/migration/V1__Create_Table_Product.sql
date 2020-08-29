CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category` ENUM('perishable', 'non_perishable') NOT NULL,
  `price` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`)
)