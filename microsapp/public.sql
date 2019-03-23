/*
Navicat PGSQL Data Transfer

Source Server         : local_c
Source Server Version : 90508
Source Host           : localhost:5432
Source Database       : jmicrocreditos
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90508
File Encoding         : 65001

Date: 2019-03-22 16:27:57
*/


-- ----------------------------
-- Sequence structure for cliente_idcliente_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."cliente_idcliente_seq";
CREATE SEQUENCE "public"."cliente_idcliente_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;

-- ----------------------------
-- Sequence structure for credito_idcredito_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."credito_idcredito_seq";
CREATE SEQUENCE "public"."credito_idcredito_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 96
 CACHE 1;
SELECT setval('"public"."credito_idcredito_seq"', 96, true);

-- ----------------------------
-- Sequence structure for distrito_ididstrito_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."distrito_ididstrito_seq";
CREATE SEQUENCE "public"."distrito_ididstrito_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 9
 CACHE 1;
SELECT setval('"public"."distrito_ididstrito_seq"', 9, true);

-- ----------------------------
-- Sequence structure for estado_idestado_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."estado_idestado_seq";
CREATE SEQUENCE "public"."estado_idestado_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."estado_idestado_seq"', 6, true);

-- ----------------------------
-- Sequence structure for estadocivil_idestadocivil_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."estadocivil_idestadocivil_seq";
CREATE SEQUENCE "public"."estadocivil_idestadocivil_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."estadocivil_idestadocivil_seq"', 6, true);

-- ----------------------------
-- Sequence structure for funcionario_idfuncionario_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."funcionario_idfuncionario_seq";
CREATE SEQUENCE "public"."funcionario_idfuncionario_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;

-- ----------------------------
-- Sequence structure for grupo_alvo_idgrupo_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."grupo_alvo_idgrupo_seq";
CREATE SEQUENCE "public"."grupo_alvo_idgrupo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 4
 CACHE 1;
SELECT setval('"public"."grupo_alvo_idgrupo_seq"', 4, true);

-- ----------------------------
-- Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."hibernate_sequence";
CREATE SEQUENCE "public"."hibernate_sequence"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 29
 CACHE 1;
SELECT setval('"public"."hibernate_sequence"', 29, true);

-- ----------------------------
-- Sequence structure for historicopagamento_idhistorico_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."historicopagamento_idhistorico_seq";
CREATE SEQUENCE "public"."historicopagamento_idhistorico_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."historicopagamento_idhistorico_seq"', 2, true);

-- ----------------------------
-- Sequence structure for instituicao_idinstituicao_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."instituicao_idinstituicao_seq";
CREATE SEQUENCE "public"."instituicao_idinstituicao_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 7
 CACHE 1;
SELECT setval('"public"."instituicao_idinstituicao_seq"', 7, true);

-- ----------------------------
-- Sequence structure for modo_pagamento_idmodo_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."modo_pagamento_idmodo_seq";
CREATE SEQUENCE "public"."modo_pagamento_idmodo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."modo_pagamento_idmodo_seq"', 6, true);

-- ----------------------------
-- Sequence structure for profile_idprofile_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."profile_idprofile_seq";
CREATE SEQUENCE "public"."profile_idprofile_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."profile_idprofile_seq"', 2, true);

-- ----------------------------
-- Sequence structure for provincia_idprovincia_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."provincia_idprovincia_seq";
CREATE SEQUENCE "public"."provincia_idprovincia_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 12
 CACHE 1;
SELECT setval('"public"."provincia_idprovincia_seq"', 12, true);

-- ----------------------------
-- Sequence structure for sexo_idSexo_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sexo_idSexo_seq";
CREATE SEQUENCE "public"."sexo_idSexo_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."sexo_idSexo_seq"', 3, true);

-- ----------------------------
-- Sequence structure for tipo_credito_idcrecredito_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."tipo_credito_idcrecredito_seq";
CREATE SEQUENCE "public"."tipo_credito_idcrecredito_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 8
 CACHE 1;
SELECT setval('"public"."tipo_credito_idcrecredito_seq"', 8, true);

-- ----------------------------
-- Sequence structure for user_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."user_user_id_seq";
CREATE SEQUENCE "public"."user_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 26
 CACHE 1;
SELECT setval('"public"."user_user_id_seq"', 26, true);

-- ----------------------------
-- Table structure for cliente
-- ----------------------------
DROP TABLE IF EXISTS "public"."cliente";
CREATE TABLE "public"."cliente" (
"idcliente" int4 NOT NULL,
"idestadocivil" int4 NOT NULL,
"nr_bi" varchar(32) COLLATE "default" NOT NULL,
"iddistrito" int4 NOT NULL,
"linhaendereco1" varchar(255) COLLATE "default",
"linhaendereco2" varchar(255) COLLATE "default",
"contacto1" varchar(255) COLLATE "default" NOT NULL,
"contacto2" varchar(255) COLLATE "default",
"idsexo" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of cliente
-- ----------------------------
INSERT INTO "public"."cliente" VALUES ('19', '2', '181901010', '2', 'Eduaro Mondlane', 'Natite', '819201010', '1919299', '1');
INSERT INTO "public"."cliente" VALUES ('20', '2', '09182882', '2', 'Pemba 1', 'Pemba 2', '19289100', '1828299', '1');
INSERT INTO "public"."cliente" VALUES ('21', '2', '117818', '2', 'xxx', 'gggg', '11781881', '919119', '1');
INSERT INTO "public"."cliente" VALUES ('22', '2', '192929919', '2', 'arrartatt', 'iaiiaiia', '45455', '5454554', '1');
INSERT INTO "public"."cliente" VALUES ('23', '2', '0101020301A', '4', 'Natite', 'Expannsao', '849018210', '8662719', '2');
INSERT INTO "public"."cliente" VALUES ('24', '2', '1902087', '2', 'Expansao Pemba', null, '84901910', '88392001', '2');
INSERT INTO "public"."cliente" VALUES ('25', '3', '1010300', '2', 'Natite', '', '09888', '85444', '1');
INSERT INTO "public"."cliente" VALUES ('26', '3', '10102002', '2', 'Natite', '', '15116', '86019292', '1');

-- ----------------------------
-- Table structure for credito
-- ----------------------------
DROP TABLE IF EXISTS "public"."credito";
CREATE TABLE "public"."credito" (
"idcredito" int4 DEFAULT nextval('credito_idcredito_seq'::regclass) NOT NULL,
"valor" float8 NOT NULL,
"data_emprestimo" date NOT NULL,
"data_pagamento" date NOT NULL,
"nr_max_pag" int4 NOT NULL,
"idcliente" int4 NOT NULL,
"idtipocredito" int4,
"idestado" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of credito
-- ----------------------------
INSERT INTO "public"."credito" VALUES ('45', '3000', '2018-03-31', '2018-03-16', '2', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('46', '6000', '2018-03-31', '2018-03-21', '2', '21', '3', '2');
INSERT INTO "public"."credito" VALUES ('47', '3000', '2018-03-07', '2018-03-31', '2', '21', '3', '2');
INSERT INTO "public"."credito" VALUES ('51', '3000', '2018-03-31', '2018-03-07', '2', '21', '2', '1');
INSERT INTO "public"."credito" VALUES ('77', '4000', '2019-03-30', '2019-03-05', '2', '21', '1', '1');
INSERT INTO "public"."credito" VALUES ('78', '5000', '2019-03-31', '2019-03-08', '2', '21', '2', '1');
INSERT INTO "public"."credito" VALUES ('79', '4000', '2019-03-31', '2019-03-22', '2', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('80', '6000', '2019-03-05', '2019-03-28', '2', '21', '1', '1');
INSERT INTO "public"."credito" VALUES ('81', '4664', '2019-03-20', '2019-04-20', '3', '21', '1', '1');
INSERT INTO "public"."credito" VALUES ('82', '4500', '2019-03-20', '2019-04-20', '4', '21', '2', '1');
INSERT INTO "public"."credito" VALUES ('83', '4664', '2019-03-20', '2019-04-20', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('84', '4664', '2019-03-20', '2019-04-20', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('85', '5830', '2019-03-20', '2019-03-30', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('86', '5830', '2019-03-01', '2019-03-30', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('87', '11250', '2019-03-30', '2019-03-01', '4', '21', '4', '1');
INSERT INTO "public"."credito" VALUES ('88', '58300', '2019-03-20', '2019-04-20', '3', '21', '1', '1');
INSERT INTO "public"."credito" VALUES ('89', '56250', '2019-03-20', '2019-04-20', '4', '21', '2', '1');
INSERT INTO "public"."credito" VALUES ('90', '58300', '2019-03-20', '2019-04-20', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('91', '56250', '2019-03-20', '2019-04-20', '4', '21', '4', '1');
INSERT INTO "public"."credito" VALUES ('92', '22500', '2019-03-06', '2019-03-30', '4', '21', '2', '1');
INSERT INTO "public"."credito" VALUES ('93', '9328', '2019-03-08', '2019-07-31', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('94', '33750', '2019-03-01', '2019-04-01', '4', '21', '4', '1');
INSERT INTO "public"."credito" VALUES ('95', '4664', '2019-03-21', '2019-04-06', '4', '21', '3', '1');
INSERT INTO "public"."credito" VALUES ('96', '4500', '2019-03-21', '2019-04-06', '4', '21', '2', '1');

-- ----------------------------
-- Table structure for creditoconsumo
-- ----------------------------
DROP TABLE IF EXISTS "public"."creditoconsumo";
CREATE TABLE "public"."creditoconsumo" (
"idcredito" int2 NOT NULL,
"idinstituicao" int2 NOT NULL,
"funcao" varchar(255) COLLATE "default",
"contactogestor" varchar(255) COLLATE "default",
"titularconta" varchar(255) COLLATE "default",
"nrconta" varchar(255) COLLATE "default",
"nomebanco" varchar(255) COLLATE "default",
"urldeclaracaoservico" varchar(255) COLLATE "default",
"urlbi" varchar(255) COLLATE "default",
"urlextratobancario" varchar(255) COLLATE "default",
"urloutro" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of creditoconsumo
-- ----------------------------
INSERT INTO "public"."creditoconsumo" VALUES ('77', '1', 'GESTOR', '82772891', 'Raimundo Jose', '1818192002001', 'bci', 'Certificates.pdf', 'Certificates1.pdf', 'Certificats_en.pdf', 'Certificates.pdf');
INSERT INTO "public"."creditoconsumo" VALUES ('80', '1', 'GESTOR', '82772891', 'Raimundo Jose', '1818192002001', 'bci', 'Certificates1.pdf', 'Certificates1.pdf', 'CV - Raimundo Jose_en.pdf', 'CV - Raimundo Jose.pdf');
INSERT INTO "public"."creditoconsumo" VALUES ('81', '1', 'GESTOR', '82772891', 'Raimundo Jose', '1818192002001', 'bci', 'Certificates1.pdf', 'Certificats_en.pdf', 'Certificates1.pdf', 'Certificates.pdf');
INSERT INTO "public"."creditoconsumo" VALUES ('88', '5', 'CTA', '82772891', 'Raimundo Jose', '1818192002001', 'BCI', 'Certificates.pdf', 'Certificats_en.pdf', 'Certificates.pdf', 'Curriculum Vitae.pdf');

-- ----------------------------
-- Table structure for creditonegocio
-- ----------------------------
DROP TABLE IF EXISTS "public"."creditonegocio";
CREATE TABLE "public"."creditonegocio" (
"idcredito" int2 NOT NULL,
"testemunha1" varchar(255) COLLATE "default",
"testemunha2" varchar(255) COLLATE "default",
"bem" text COLLATE "default",
"urldeclaracao" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of creditonegocio
-- ----------------------------
INSERT INTO "public"."creditonegocio" VALUES ('51', 'Universidade Lurio', 'Jonas Patricio', 'Viatura', 'JEEIntro.pdf');
INSERT INTO "public"."creditonegocio" VALUES ('78', 'Ruqui Simao', 'Antonio Camilo', 'Motoriadas', 'Certificates1.pdf');
INSERT INTO "public"."creditonegocio" VALUES ('82', 'Ruqui Simao', 'Antonio Camilo', 'Motoriadas', 'Certificates.pdf');
INSERT INTO "public"."creditonegocio" VALUES ('89', 'Rhamen ', 'Damiao', 'Motorizada', 'Certificates.pdf');
INSERT INTO "public"."creditonegocio" VALUES ('92', 'Esmael Samuel', 'Sonia Camilo', 'Cesta de Roupa', 'Certificates1.pdf');

-- ----------------------------
-- Table structure for creditopenhor
-- ----------------------------
DROP TABLE IF EXISTS "public"."creditopenhor";
CREATE TABLE "public"."creditopenhor" (
"idcredito" int4 NOT NULL,
"urlimovel" varchar(255) COLLATE "default",
"urldecpenhor" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of creditopenhor
-- ----------------------------
INSERT INTO "public"."creditopenhor" VALUES ('86', 'Certificates1.pdf', 'CV - Raimundo Jose.pdf');
INSERT INTO "public"."creditopenhor" VALUES ('90', 'Certificats_en.pdf', 'CV - Raimundo Jose_en.pdf');

-- ----------------------------
-- Table structure for creditovip
-- ----------------------------
DROP TABLE IF EXISTS "public"."creditovip";
CREATE TABLE "public"."creditovip" (
"idcredito" int4 NOT NULL,
"urldeclaracaohonra" varchar(255) COLLATE "default",
"credor" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of creditovip
-- ----------------------------
INSERT INTO "public"."creditovip" VALUES ('87', 'CV - Raimundo Jose.pdf', 'xxxxxxxxxxxxx');
INSERT INTO "public"."creditovip" VALUES ('91', 'Certificats_en.pdf', 'Nunca tivemos problemas com este senhor ');

-- ----------------------------
-- Table structure for distrito
-- ----------------------------
DROP TABLE IF EXISTS "public"."distrito";
CREATE TABLE "public"."distrito" (
"ididstrito" int4 DEFAULT nextval('distrito_ididstrito_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL,
"idprovincia" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of distrito
-- ----------------------------
INSERT INTO "public"."distrito" VALUES ('2', 'Pemba', '1');
INSERT INTO "public"."distrito" VALUES ('4', 'Montepuez', '1');
INSERT INTO "public"."distrito" VALUES ('5', 'Chiure', '1');
INSERT INTO "public"."distrito" VALUES ('7', 'Namuno', '1');
INSERT INTO "public"."distrito" VALUES ('8', 'Balama', '1');
INSERT INTO "public"."distrito" VALUES ('9', 'Ancuabe', '1');

-- ----------------------------
-- Table structure for estado
-- ----------------------------
DROP TABLE IF EXISTS "public"."estado";
CREATE TABLE "public"."estado" (
"idestado" int8 DEFAULT nextval('estado_idestado_seq'::regclass) NOT NULL,
"status" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of estado
-- ----------------------------
INSERT INTO "public"."estado" VALUES ('1', 'Pago');
INSERT INTO "public"."estado" VALUES ('2', 'Em Andamento');
INSERT INTO "public"."estado" VALUES ('4', 'Nao Pago');
INSERT INTO "public"."estado" VALUES ('5', 'Cancelado');
INSERT INTO "public"."estado" VALUES ('6', 'Pendente');

-- ----------------------------
-- Table structure for estadocivil
-- ----------------------------
DROP TABLE IF EXISTS "public"."estadocivil";
CREATE TABLE "public"."estadocivil" (
"idestadocivil" int4 DEFAULT nextval('estadocivil_idestadocivil_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of estadocivil
-- ----------------------------
INSERT INTO "public"."estadocivil" VALUES ('2', 'Casado(a)');
INSERT INTO "public"."estadocivil" VALUES ('3', 'Solteiro(a)');
INSERT INTO "public"."estadocivil" VALUES ('5', 'Viuvo(a)');
INSERT INTO "public"."estadocivil" VALUES ('6', 'Divorciado(a)');

-- ----------------------------
-- Table structure for funcionario
-- ----------------------------
DROP TABLE IF EXISTS "public"."funcionario";
CREATE TABLE "public"."funcionario" (
"idfuncionario" int4 DEFAULT nextval('funcionario_idfuncionario_seq'::regclass) NOT NULL,
"idinstituicao" int4 NOT NULL,
"funcao" varchar(255) COLLATE "default" NOT NULL,
"proveniencia" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of funcionario
-- ----------------------------

-- ----------------------------
-- Table structure for grupoalvo
-- ----------------------------
DROP TABLE IF EXISTS "public"."grupoalvo";
CREATE TABLE "public"."grupoalvo" (
"idgrupo" int4 DEFAULT nextval('grupo_alvo_idgrupo_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL,
"idtipo_credito" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of grupoalvo
-- ----------------------------
INSERT INTO "public"."grupoalvo" VALUES ('3', 'Funcionarios', '1');
INSERT INTO "public"."grupoalvo" VALUES ('4', 'Investidores', '2');
INSERT INTO "public"."grupoalvo" VALUES ('5', 'Comerciantes', '3');

-- ----------------------------
-- Table structure for historicopagamento
-- ----------------------------
DROP TABLE IF EXISTS "public"."historicopagamento";
CREATE TABLE "public"."historicopagamento" (
"idcredito" int4 NOT NULL,
"idmodopagamento" int4 NOT NULL,
"data" date NOT NULL,
"valor" float8 NOT NULL,
"ordem" int4 NOT NULL,
"idhistorico" int4 DEFAULT nextval('historicopagamento_idhistorico_seq'::regclass) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of historicopagamento
-- ----------------------------
INSERT INTO "public"."historicopagamento" VALUES ('78', '2', '2019-06-02', '3000', '1', '1');
INSERT INTO "public"."historicopagamento" VALUES ('96', '5', '2019-03-21', '1125', '1', '2');

-- ----------------------------
-- Table structure for instituicao
-- ----------------------------
DROP TABLE IF EXISTS "public"."instituicao";
CREATE TABLE "public"."instituicao" (
"idinstituicao" int4 DEFAULT nextval('instituicao_idinstituicao_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL,
"contacto_gestor" varchar(255) COLLATE "default" NOT NULL,
"linhaendereco1" varchar(255) COLLATE "default",
"linhaendereco2" varchar(255) COLLATE "default",
"iddistrito" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of instituicao
-- ----------------------------
INSERT INTO "public"."instituicao" VALUES ('1', 'INATER', '84901821', 'Expansao', 'Rua 12', '2');
INSERT INTO "public"."instituicao" VALUES ('5', 'UNILURIO', '828144809', 'Natite', 'rua 3', '5');
INSERT INTO "public"."instituicao" VALUES ('7', 'FIPAG', '867281993', 'Zona 5', 'Rua das Flores', '2');

-- ----------------------------
-- Table structure for modopagamento
-- ----------------------------
DROP TABLE IF EXISTS "public"."modopagamento";
CREATE TABLE "public"."modopagamento" (
"idmodo" int4 DEFAULT nextval('modo_pagamento_idmodo_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of modopagamento
-- ----------------------------
INSERT INTO "public"."modopagamento" VALUES ('2', 'Prestação');
INSERT INTO "public"."modopagamento" VALUES ('3', 'Sistema Bancario');
INSERT INTO "public"."modopagamento" VALUES ('4', 'Cash');
INSERT INTO "public"."modopagamento" VALUES ('5', 'Cheque');
INSERT INTO "public"."modopagamento" VALUES ('6', 'Transferencia');

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS "public"."profile";
CREATE TABLE "public"."profile" (
"idprofile" int4 DEFAULT nextval('profile_idprofile_seq'::regclass) NOT NULL,
"instituicao" varchar(255) COLLATE "default",
"nomefantasia" varchar(255) COLLATE "default",
"gestor" varchar(255) COLLATE "default",
"contacto" char(255) COLLATE "default",
"ulrimagem" char(255) COLLATE "default",
"email" char(255) COLLATE "default",
"endereco" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO "public"."profile" VALUES ('1', 'Operador de Microcreditox', 'Estrangelal Jamal Microcreditos, E,.I', 'Jamal Jamaldinho', '82929003                                                                                                                                                                                                                                                       ', 'Certificates1.pdf                                                                                                                                                                                                                                              ', 'admin@gmail.com                                                                                                                                                                                                                                                ', 'Expansao, Via ANE');

-- ----------------------------
-- Table structure for provincia
-- ----------------------------
DROP TABLE IF EXISTS "public"."provincia";
CREATE TABLE "public"."provincia" (
"idprovincia" int4 DEFAULT nextval('provincia_idprovincia_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of provincia
-- ----------------------------
INSERT INTO "public"."provincia" VALUES ('1', 'Cabo Delgado');
INSERT INTO "public"."provincia" VALUES ('2', 'Nampula');
INSERT INTO "public"."provincia" VALUES ('5', 'Niassa');
INSERT INTO "public"."provincia" VALUES ('6', 'Maputo');
INSERT INTO "public"."provincia" VALUES ('7', 'Zambezia');
INSERT INTO "public"."provincia" VALUES ('8', 'Tete');
INSERT INTO "public"."provincia" VALUES ('9', 'Manica');
INSERT INTO "public"."provincia" VALUES ('10', 'Inhambane');
INSERT INTO "public"."provincia" VALUES ('11', 'Sofala');
INSERT INTO "public"."provincia" VALUES ('12', 'Gaza');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "public"."role";
CREATE TABLE "public"."role" (
"role_id" int4 NOT NULL,
"role" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO "public"."role" VALUES ('1', 'ADMIN');
INSERT INTO "public"."role" VALUES ('2', 'STANDARD');
INSERT INTO "public"."role" VALUES ('3', 'CLIENT');

-- ----------------------------
-- Table structure for sexo
-- ----------------------------
DROP TABLE IF EXISTS "public"."sexo";
CREATE TABLE "public"."sexo" (
"idsexo" int8 DEFAULT nextval('"sexo_idSexo_seq"'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sexo
-- ----------------------------
INSERT INTO "public"."sexo" VALUES ('1', 'Masculino');
INSERT INTO "public"."sexo" VALUES ('2', 'Femenino');

-- ----------------------------
-- Table structure for tipocredito
-- ----------------------------
DROP TABLE IF EXISTS "public"."tipocredito";
CREATE TABLE "public"."tipocredito" (
"idcrecredito" int4 DEFAULT nextval('tipo_credito_idcrecredito_seq'::regclass) NOT NULL,
"descricao" varchar(255) COLLATE "default" NOT NULL,
"pgto" int4,
"juro" float8,
"status" bool,
"data" date
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tipocredito
-- ----------------------------
INSERT INTO "public"."tipocredito" VALUES ('1', 'Credito ao Consumo', '3', '16.6', 't', '2019-03-01');
INSERT INTO "public"."tipocredito" VALUES ('2', 'Credito para Negócio', '4', '12.5', 't', '2019-03-04');
INSERT INTO "public"."tipocredito" VALUES ('3', 'Credito pela Penhora', '4', '16.6', 't', '2019-04-03');
INSERT INTO "public"."tipocredito" VALUES ('4', 'Credito VIP', '4', '12.5', 't', '2019-01-20');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
"active" int4,
"email" varchar(255) COLLATE "default",
"last_name" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
"password" varchar(255) COLLATE "default",
"user_id" int8 DEFAULT nextval('user_user_id_seq'::regclass) NOT NULL,
"data_added" date,
"role_id" int2
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "public"."user" VALUES ('1', 'user@gmail.com', 'Jose', 'Raimundo', '123', '19', '2019-02-26', '2');
INSERT INTO "public"."user" VALUES ('1', 'client@gmail.com', 'Mario', 'Maria Camilo', '123', '20', '2019-02-26', '3');
INSERT INTO "public"."user" VALUES ('1', 'admin@gmail.com', 'Ali', 'Felermino', '123', '21', '2019-02-26', '1');
INSERT INTO "public"."user" VALUES ('1', 'h@gamma', 'Antonio', 'Joanito ', '122345', '22', '2019-03-07', '3');
INSERT INTO "public"."user" VALUES ('1', 'joanaernesto@gmail.com', 'Ernesto', 'Joana', '123', '23', '2019-03-20', '3');
INSERT INTO "public"."user" VALUES ('1', 'celia@gmail.com', 'Panquene', 'Celia Jamal', '123', '24', '2019-03-20', '3');
INSERT INTO "public"."user" VALUES ('1', 'jamali@gmail.com', 'Gracia', 'Jamilti', '123', '25', '2019-03-20', '2');
INSERT INTO "public"."user" VALUES ('1', 'fred@gmail.com', 'nelson', 'Fred', '123', '26', '2019-03-20', '2');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."cliente_idcliente_seq" OWNED BY "cliente"."idcliente";
ALTER SEQUENCE "public"."credito_idcredito_seq" OWNED BY "credito"."idcredito";
ALTER SEQUENCE "public"."distrito_ididstrito_seq" OWNED BY "distrito"."ididstrito";
ALTER SEQUENCE "public"."estado_idestado_seq" OWNED BY "estado"."idestado";
ALTER SEQUENCE "public"."estadocivil_idestadocivil_seq" OWNED BY "estadocivil"."idestadocivil";
ALTER SEQUENCE "public"."funcionario_idfuncionario_seq" OWNED BY "funcionario"."idfuncionario";
ALTER SEQUENCE "public"."grupo_alvo_idgrupo_seq" OWNED BY "grupoalvo"."idgrupo";
ALTER SEQUENCE "public"."historicopagamento_idhistorico_seq" OWNED BY "historicopagamento"."idhistorico";
ALTER SEQUENCE "public"."instituicao_idinstituicao_seq" OWNED BY "instituicao"."idinstituicao";
ALTER SEQUENCE "public"."modo_pagamento_idmodo_seq" OWNED BY "modopagamento"."idmodo";
ALTER SEQUENCE "public"."profile_idprofile_seq" OWNED BY "profile"."idprofile";
ALTER SEQUENCE "public"."provincia_idprovincia_seq" OWNED BY "provincia"."idprovincia";
ALTER SEQUENCE "public"."sexo_idSexo_seq" OWNED BY "sexo"."idsexo";
ALTER SEQUENCE "public"."tipo_credito_idcrecredito_seq" OWNED BY "tipocredito"."idcrecredito";
ALTER SEQUENCE "public"."user_user_id_seq" OWNED BY "user"."user_id";

-- ----------------------------
-- Primary Key structure for table cliente
-- ----------------------------
ALTER TABLE "public"."cliente" ADD PRIMARY KEY ("idcliente");

-- ----------------------------
-- Primary Key structure for table credito
-- ----------------------------
ALTER TABLE "public"."credito" ADD PRIMARY KEY ("idcredito");

-- ----------------------------
-- Primary Key structure for table creditoconsumo
-- ----------------------------
ALTER TABLE "public"."creditoconsumo" ADD PRIMARY KEY ("idcredito", "idinstituicao");

-- ----------------------------
-- Primary Key structure for table creditonegocio
-- ----------------------------
ALTER TABLE "public"."creditonegocio" ADD PRIMARY KEY ("idcredito");

-- ----------------------------
-- Primary Key structure for table creditopenhor
-- ----------------------------
ALTER TABLE "public"."creditopenhor" ADD PRIMARY KEY ("idcredito");

-- ----------------------------
-- Primary Key structure for table creditovip
-- ----------------------------
ALTER TABLE "public"."creditovip" ADD PRIMARY KEY ("idcredito");

-- ----------------------------
-- Primary Key structure for table distrito
-- ----------------------------
ALTER TABLE "public"."distrito" ADD PRIMARY KEY ("ididstrito");

-- ----------------------------
-- Primary Key structure for table estado
-- ----------------------------
ALTER TABLE "public"."estado" ADD PRIMARY KEY ("idestado");

-- ----------------------------
-- Primary Key structure for table estadocivil
-- ----------------------------
ALTER TABLE "public"."estadocivil" ADD PRIMARY KEY ("idestadocivil");

-- ----------------------------
-- Primary Key structure for table funcionario
-- ----------------------------
ALTER TABLE "public"."funcionario" ADD PRIMARY KEY ("idfuncionario");

-- ----------------------------
-- Primary Key structure for table grupoalvo
-- ----------------------------
ALTER TABLE "public"."grupoalvo" ADD PRIMARY KEY ("idgrupo");

-- ----------------------------
-- Primary Key structure for table historicopagamento
-- ----------------------------
ALTER TABLE "public"."historicopagamento" ADD PRIMARY KEY ("idhistorico");

-- ----------------------------
-- Primary Key structure for table instituicao
-- ----------------------------
ALTER TABLE "public"."instituicao" ADD PRIMARY KEY ("idinstituicao");

-- ----------------------------
-- Primary Key structure for table modopagamento
-- ----------------------------
ALTER TABLE "public"."modopagamento" ADD PRIMARY KEY ("idmodo");

-- ----------------------------
-- Primary Key structure for table profile
-- ----------------------------
ALTER TABLE "public"."profile" ADD PRIMARY KEY ("idprofile");

-- ----------------------------
-- Primary Key structure for table provincia
-- ----------------------------
ALTER TABLE "public"."provincia" ADD PRIMARY KEY ("idprovincia");

-- ----------------------------
-- Primary Key structure for table role
-- ----------------------------
ALTER TABLE "public"."role" ADD PRIMARY KEY ("role_id");

-- ----------------------------
-- Primary Key structure for table sexo
-- ----------------------------
ALTER TABLE "public"."sexo" ADD PRIMARY KEY ("idsexo");

-- ----------------------------
-- Primary Key structure for table tipocredito
-- ----------------------------
ALTER TABLE "public"."tipocredito" ADD PRIMARY KEY ("idcrecredito");

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD PRIMARY KEY ("user_id");

-- ----------------------------
-- Foreign Key structure for table "public"."cliente"
-- ----------------------------
ALTER TABLE "public"."cliente" ADD FOREIGN KEY ("idcliente") REFERENCES "public"."user" ("user_id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."cliente" ADD FOREIGN KEY ("idsexo") REFERENCES "public"."sexo" ("idsexo") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."cliente" ADD FOREIGN KEY ("idestadocivil") REFERENCES "public"."estadocivil" ("idestadocivil") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."cliente" ADD FOREIGN KEY ("iddistrito") REFERENCES "public"."distrito" ("ididstrito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."credito"
-- ----------------------------
ALTER TABLE "public"."credito" ADD FOREIGN KEY ("idcredito") REFERENCES "public"."credito" ("idcredito") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."credito" ADD FOREIGN KEY ("idtipocredito") REFERENCES "public"."tipocredito" ("idcrecredito") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."credito" ADD FOREIGN KEY ("idestado") REFERENCES "public"."estado" ("idestado") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."credito" ADD FOREIGN KEY ("idcliente") REFERENCES "public"."cliente" ("idcliente") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."creditoconsumo"
-- ----------------------------
ALTER TABLE "public"."creditoconsumo" ADD FOREIGN KEY ("idinstituicao") REFERENCES "public"."instituicao" ("idinstituicao") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."creditoconsumo" ADD FOREIGN KEY ("idcredito") REFERENCES "public"."credito" ("idcredito") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."creditonegocio"
-- ----------------------------
ALTER TABLE "public"."creditonegocio" ADD FOREIGN KEY ("idcredito") REFERENCES "public"."credito" ("idcredito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."creditopenhor"
-- ----------------------------
ALTER TABLE "public"."creditopenhor" ADD FOREIGN KEY ("idcredito") REFERENCES "public"."credito" ("idcredito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."creditovip"
-- ----------------------------
ALTER TABLE "public"."creditovip" ADD FOREIGN KEY ("idcredito") REFERENCES "public"."credito" ("idcredito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."distrito"
-- ----------------------------
ALTER TABLE "public"."distrito" ADD FOREIGN KEY ("idprovincia") REFERENCES "public"."provincia" ("idprovincia") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."funcionario"
-- ----------------------------
ALTER TABLE "public"."funcionario" ADD FOREIGN KEY ("idinstituicao") REFERENCES "public"."instituicao" ("idinstituicao") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."funcionario" ADD FOREIGN KEY ("idfuncionario") REFERENCES "public"."cliente" ("idcliente") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."grupoalvo"
-- ----------------------------
ALTER TABLE "public"."grupoalvo" ADD FOREIGN KEY ("idtipo_credito") REFERENCES "public"."tipocredito" ("idcrecredito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."historicopagamento"
-- ----------------------------
ALTER TABLE "public"."historicopagamento" ADD FOREIGN KEY ("idmodopagamento") REFERENCES "public"."modopagamento" ("idmodo") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."historicopagamento" ADD FOREIGN KEY ("idcredito") REFERENCES "public"."credito" ("idcredito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."instituicao"
-- ----------------------------
ALTER TABLE "public"."instituicao" ADD FOREIGN KEY ("iddistrito") REFERENCES "public"."distrito" ("ididstrito") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."user"
-- ----------------------------
ALTER TABLE "public"."user" ADD FOREIGN KEY ("role_id") REFERENCES "public"."role" ("role_id") ON DELETE CASCADE ON UPDATE CASCADE;
