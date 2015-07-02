-- liquibase formatted sql
-- changeset jwilliams:CreateCeeDatabase
CREATE DATABASE `cee` /*!40100 DEFAULT CHARACTER SET utf8 */;

-- changeset jwilliams:Createfe_prod_claims_postprocess
CREATE TABLE `cee`.`fe_prod_claims_postprocess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fe_prod_id` int(11) DEFAULT NULL,
  `claim` varchar(10000) DEFAULT NULL,
  `date_entered` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `suspect` tinyint(4) DEFAULT '0',
  `claim_id` int(11) DEFAULT '0',
  `level` int(11) DEFAULT '0',
  `sameas_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `ind_fe_prod_id` (`fe_prod_id`),
  KEY `ind_claim` (`claim`(255)),
  KEY `ind_claim_id` (`claim_id`),
  KEY `ind_suspect` (`suspect`)
) ENGINE=InnoDB AUTO_INCREMENT=5112521 DEFAULT CHARSET=utf8;
